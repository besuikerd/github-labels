package msr.scraper

import play.api.libs.json.Json

case class ScraperConfig(
  repos: Seq[String]
)

object ScraperConfig{
  implicit val jsonFormat = Json.format[ScraperConfig]
}

