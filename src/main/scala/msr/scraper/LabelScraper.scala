package msr.scraper

import msr.api.{User, Issue, Label, ApiHandler}
import Label.jsonFormat
import User.jsonFormat
import Issue.jsonFormat
import play.api.libs.json.{Reads, JsValue}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class LabelScraper(config:ScraperConfig, apiHandler:ApiHandler) {

  def getAllLabels():Future[Seq[Label]] = {
    val requests = for{
      repo <- config.repos
    } yield apiHandler.request(s"/repos/$repo/labels").map(_.as[Seq[Label]])
    Future.sequence(requests).map(_.flatten.distinct)
  }

  def getUser(name:String):Future[User] = apiHandler.request(s"users/$name").map(_.as[User])


  def getIssuesForLabels(repo:String, labels:String*):Future[Seq[Issue]] = {
    apiHandler.request(s"repos/$repo/issues", "labels" -> labels.mkString(",")).map(_.as[Seq[Issue]])
  }

  def getIssuesForRepo(repo:String):Future[Seq[Issue]] = {
    apiHandler.requestWithPagination(s"repos/$repo/issues",
      "state" -> "all",
      "filter" -> "all"
    ).map(_.flatMap(_.as[Seq[Issue]]))
  }
}
