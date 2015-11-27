package msr

import com.ning.http.client.AsyncHttpClientConfig
import msr.api.{Label, ApiHandler}
import msr.scraper.{LabelScraper, ScraperConfig}
import play.api.libs.json.Json
import play.api.libs.ws.WS
import play.api.libs.ws.ning.{NingAsyncHttpClientConfigBuilder, NingWSClient}
import scala.concurrent.Future
import scala.io.Source
import resource._
import scala.concurrent.ExecutionContext.Implicits.global
import msr.scraper.ScraperConfig.jsonFormat

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

    scraper.getIssuesForRepo("OpenRA/OpenRA").foreach{ issues =>
      println("# of issues: " + issues.size)
    }
  }
}