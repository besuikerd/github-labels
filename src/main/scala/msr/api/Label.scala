package msr.api

import play.api.libs.json.Json

case class Label(
  url:String,
  name:String,
  color:String
)

object Label{
  implicit val jsonFormat = Json.format[Label]
}
