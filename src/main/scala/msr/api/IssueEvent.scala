package msr.api

import org.joda.time.DateTime

case class IssueEvent(
  id: Int,
  url: String,
  event: String,
  created_at: DateTime,
  issue: Issue,
  actor: User,
  label: Option[Label]
)