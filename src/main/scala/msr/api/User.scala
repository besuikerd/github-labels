package msr.api

import play.api.libs.json.Json

case class User(
  login:String,
  id:Int,
  `type` : String,
  site_admin:Boolean
)