package models

import play.api.Play.current
import play.api.db.DB
import slick.driver.PostgresDriver.api._

import scala.concurrent.duration.Duration
import scala.concurrent.Await

object PlayerDAO {
  val players = TableQuery[Players]

  private def db: Database = Database.forDataSource(DB.getDataSource())

  def insert(player: Player): Option[Player] = {
    val isCompleted = {
      val f = db.run((players += player).transactionally)
      try {
        Await.result(f, Duration.Inf)
        true
      } catch {
        case e: Exception => println("exception caught: " + e)
          false
      } finally db.close()
    }
    if (isCompleted) Some(player) else None
  }
}

