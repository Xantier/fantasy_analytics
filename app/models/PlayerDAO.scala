package models

import play.api.Play.current
import play.api.db.DB
import slick.driver.PostgresDriver.api._

import scala.concurrent.Future

object PlayerDAO {
  val players = TableQuery[Players]

  private def db: Database = Database.forDataSource(DB.getDataSource())

  def insert(player: Player): Future[Int] =
    db.run(players += player)
}

