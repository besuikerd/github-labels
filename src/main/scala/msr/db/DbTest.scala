package msr.db

import msr.api.User
import slick.dbio.DBIOAction

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}
import slick.driver.MySQLDriver.api._

import DatabaseTables._

object DbTest extends App{

  val db = Database.forConfig("mysql");


//  val allSchemas = Seq(
//    users.schema,
//    repos.schema,
//    issueEvents.schema,
//    issues.schema
//  )
//
//  val regenerateDb = DBIOAction.seq(
//    DBIOAction.sequence(allSchemas.reverse.map(_.drop)),
//    DBIOAction.sequence(allSchemas.map(_.create))
//  )
//
//  Await.result(db.run(regenerateDb), Duration.Inf)

}
