package msr

import com.ning.http.client.AsyncHttpClientConfig
import msr.api.{IssueEvent, Label, ApiHandler}
import msr.db.DatabaseTables
import msr.scraper.{LabelScraper, ScraperConfig}
import play.api.libs.json.Json
import msr.api.ApiReads._
import play.api.libs.ws.WS
import play.api.libs.ws.ning.{NingAsyncHttpClientConfigBuilder, NingWSClient}
import slick.dbio.DBIOAction
import scala.concurrent.Future
import scala.io.Source
import resource._
import scala.concurrent.ExecutionContext.Implicits.global
import msr.scraper.ScraperConfig.jsonFormat
import scala.util.Success
import scala.util.Failure
import slick.driver.MySQLDriver.api._


object GithubApiTest extends App{

  val secretsFilename = "secrets.json"
  val scraperConfigFilename = "scraper-config.json"

  for{
    secrets <- managed(getClass.getClassLoader.getResourceAsStream(secretsFilename)).map(Json.parse)
    apiKey <- (secrets \ "github-access-token").asOpt[String]
    scraperConfig <- managed(getClass.getClassLoader.getResourceAsStream(scraperConfigFilename)).map(Json.parse(_).as[ScraperConfig])
  } {

    val apiHandler = new ApiHandler(apiKey)
    val scraper = new LabelScraper(scraperConfig, apiHandler)

//    scraper.getAllLabels().foreach(_.foreach(println))
    scraper.getUser("besuikerd").foreach(println)

//    scraper.getIssuesForRepo("OpenRA/OpenRA").onComplete{
//      case Success(issues) =>
//        println("# of issues: " + issues.size)
//      case Failure(err) => println("could not get issuess, cause: " + err)
//    }


    apiHandler.request("repos/OpenRA/OpenRA/issues/events", ("event", "labeled")).onComplete{
      case Success(events) => {
        val mapped = events.as[Seq[IssueEvent]]
        mapped.foreach(println)

      }
      case Failure(err) => println("could not get issuess, cause: " + err)
    }
  }
}
