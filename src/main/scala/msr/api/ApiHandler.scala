package msr.api

import com.ning.http.client.AsyncHttpClientConfig
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSResponse, WSRequest}
import play.api.libs.ws.ning.{NingAsyncHttpClientConfigBuilder, NingWSClient}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class ApiHandler(apiKey:String) {
  val config = new NingAsyncHttpClientConfigBuilder().build
  val builder = new AsyncHttpClientConfig.Builder(config)
  val client = new NingWSClient(builder.build)

  val urlBase = "https://api.github.com"

  def request(path: String, args: (String, String)*): Future[JsValue] = {

    val response = mkRequest(mkUrl(path, args: _*)).get

    response.foreach(println)
    response.map(res => Json.parse(res.body))
  }

  def requestWithPagination(path: String, args: (String, String)*): Future[Seq[JsValue]] = {
    def firstRequest = mkRequest(mkUrl(path, (args :+ ("per_page" -> "100")): _*)).get
    firstRequest.flatMap[Seq[JsValue]]{ response =>
      val firstResult = Future.successful(Seq(Json.parse(response.body)))

      response.header("Link") match {
        case Some(link) => {
          val links = parseLinkHeader(link)

          links.get("last") match{
            case Some(lastLink) => {
                val pattern = """.*page=(\d+)""".r
                lastLink match {
                  case pattern(page) => {
                    val futures = for (i <- 2 to page.toInt) yield {
                      val req = mkRequest(mkUrl(path, (args ++ Seq("page" -> i.toString, "per_page" -> "100")): _*)).get
                      req.map{ res =>
                        println(res)
                        Json.parse(res.body)
                      }
                    }
                    Future.sequence(Future.successful(Json.parse(response.body)) +: futures)
                  }
                  case _ => firstResult
                }
            }
            case None => firstResult
          }
        }
        case None => firstResult
      }

    }
  }


  def requestWithPagination2(path: String, args: (String, String)*): Future[Seq[JsValue]] = {

    def grabNextPage(current:Seq[JsValue])(req: Future[WSResponse]):Future[Seq[JsValue]] = {
      req.flatMap { response =>
        response.header("Link") match {
          case Some(link) => {
            val links = parseLinkHeader(link)
            links.get("next") match {
              case Some(nextUrl) => grabNextPage(current :+ Json.parse(response.body))(client.url(nextUrl).get)
              case None => Future.successful(current)
            }
          }
          case None => Future.successful(Seq(Json.parse(response.body)))
        }
      }
    }

    val firstResponse = mkRequest(mkUrl(path, args: _*)).get

    grabNextPage(Seq.empty)(firstResponse)
  }



  def parseLinkHeader(header: String): Map[String, String] = {
    val pattern = """<(.*?)>;\s*rel="(\w+)"""".r
    val list = for {
      found <- pattern.findAllMatchIn(header)
    } yield (found.group(2), found.group(1))
    list.toMap
  }

  def mkUrl(path: String, args: (String, String)*): String = s"$urlBase/${path.replaceFirst("^/", "")}" + (if (args.nonEmpty) s"?${args.map(tuple => "%s=%s".format(tuple._1, tuple._2)).mkString("&")}" else "")

  def mkRequest(url: String): WSRequest = {
    client
      .url(url)
      .withHeaders(
        "Authorization" -> s"token $apiKey"
      )
  }
}
