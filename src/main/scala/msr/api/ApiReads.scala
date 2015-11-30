package msr.api

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import play.api.libs.json._

object ApiReads {
  implicit val iso8601 = new Reads[DateTime]{
    val formatter = ISODateTimeFormat.dateTimeNoMillis()

    override def reads(json: JsValue): JsResult[DateTime] = json match{
      case JsNumber(d) => JsSuccess(new DateTime(d.toLong))
      case JsString(s) => try{
        JsSuccess(formatter.parseDateTime(s))
      } catch{
        case e:IllegalArgumentException => JsError(e.getMessage())
      }
      case other => JsError("expected date string or number, got " + other.getClass.getSimpleName())
    }
  }


  implicit val user = Json.format[User]
  implicit val pullRequest = Json.format[PullRequest]
  implicit val label = Json.format[Label]
  implicit val issue = Json.format[Issue]
  implicit val issueEvent = Json.format[IssueEvent]


}
