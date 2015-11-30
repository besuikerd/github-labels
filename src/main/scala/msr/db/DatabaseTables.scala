package msr.db

import msr.api._
import org.joda.time.DateTime
import slick.driver.MySQLDriver.api._

object DatabaseTables {

//  class UserTable(tag:Tag) extends Table[User](tag, "User"){
//    def login = column[String]("login", O.PrimaryKey)
//    def id = column[Int]("id")
//    def `type` = column[String]("type")
//    def site_admin = column[Boolean]("site_admin")
//    def * = (login, id, `type`, site_admin) <> ((User.apply _).tupled, User.unapply _)
//  }
//
//  class RepoTable(tag:Tag) extends Table[Repo](tag, "Repo"){
//    def id = column[Int]("id", O.PrimaryKey)
//
//    def ownerId = column[Int]("owner_id")
//    def owner = foreignKey("fk_owner", ownerId, users)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
//
//    def name = column[String]("name")
//    def full_name = column[String]("full_name")
//    def description = column[String]("description")
//    def url = column[String]("url")
//    def subscribers_count = column[Int]("subscribers_count")
//    def stargazers_count = column[Int]("stargazers_count")
//    def watchers_count = column[Int]("watchers_count")
//    def * = (id,owner,name,full_name,description,url,subscribers_count,stargazers_count,watchers_count) <> (Repo.tupled, Repo.unapply _)
//  }
//
//  class IssueEventTable(tag:Tag) extends Table[IssueEvent](tag, "IssueEvent"){
//    def id = column[Int]("id")
//    def url = column[String]("url")
//    def event = column[String]("event")
//    def created_at = column[DateTime]("created_at")
//
//    def issueId = column[Int]("issueId")
//    def issue = foreignKey("fk_issue", issueId, issues)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
//
//    def actorId = column[Int]("actor_id")
//    def actor = foreignKey("fk_actor", actorId, users)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
//
//    def label = column[Option[String]]("label")
//    def * = (id,url,event,created_at,issue,actor,label) <> (IssueEvent.tupled, IssueEvent.unapply _)
//  }
//
//  class IssueTable(tag:Tag) extends Table[Issue](tag, "Issue"){
//    def id = column[Int]("id")
//    def url = column[String]("url")
//    def state = column[String]("state")
//    def title = column[String]("title")
//    def body = column[String]("body")
//    def user = column[User]("user")
//    def labels = column[Seq]("labels")[Label]
//
//    def assigneeId = column[Option[Int]]("assignee_id")
//    def assignee = foreignKey("fk_assignee", assigneeId, users)(_.id.?, onUpdate=ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
//
//    def comments = column[Int]("comments")
//    def locked = column[Boolean]("locked")
//    def pull_request = column[Option[PullRequest]]("pull_request")
//
//    def * = (id,url,state,title,body,user,labels,assignee,comments,locked,pull_request) <> ((Issue.tupled, Issue.unapply _)
//  }
//
//  val users = TableQuery[UserTable]
//  val repos = TableQuery[RepoTable]
//  val issueEvents = TableQuery[IssueEventTable]
//  val issues = TableQuery[IssueTable]

}
