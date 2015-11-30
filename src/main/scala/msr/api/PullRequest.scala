package msr.api

import play.api.libs.json.Json

case class PullRequest(
  url:String,
  html_url:String,
  diff_url:String,
  patch_url:String
)