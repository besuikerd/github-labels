package msr.api

import play.api.libs.json.Json

case class Issue(
  id:Int,
  url:String,
  state:String,
  title:String,
  body:String,
  user:User,
  labels:Seq[Label],
  assignee:Option[User],
  comments:Int,
  locked:Boolean,
  pull_request:Option[PullRequest]
){
  def isPullRequest:Boolean = pull_request.isDefined
}