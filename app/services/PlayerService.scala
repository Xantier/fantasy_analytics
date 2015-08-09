package services

import models._
import play.api.libs.iteratee.Enumerator
import play.api.libs.ws._
import play.api.mvc.{Action, ResponseHeader, Result}
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

object PlayerService {

  def construct(res: WSResponse): Player = {

    Player(
      None,
      (res.json \ "code").as[Int],
      (res.json \ "chance_of_playing_this_round").asOpt[Int],
      (res.json \ "chance_of_playing_next_round").asOpt[Int],
      transfers = Transfers(
        (res.json \ "transfers_out").as[Int],
        (res.json \ "transfers_in").as[Int],
        (res.json \ "transfers_out_event").as[Int],
        (res.json \ "transfers_in_event").as[Int],
        (res.json \ "loans_in").as[Int],
        (res.json \ "loans_out").as[Int],
        (res.json \ "loaned_in").as[Int],
        (res.json \ "loaned_out").as[Int]
      ),
      points = Points(
        (res.json \ "total_points").as[Int],
        (res.json \ "minutes").as[Int],
        (res.json \ "goals_scored").as[Int],
        (res.json \ "assists").as[Int],
        (res.json \ "clean_sheets").as[Int],
        (res.json \ "goals_conceded").as[Int],
        (res.json \ "own_goals").as[Int],
        (res.json \ "penalties_saved").as[Int],
        (res.json \ "penalties_missed").as[Int],
        (res.json \ "yellow_cards").as[Int],
        (res.json \ "red_cards").as[Int],
        (res.json \ "saves").as[Int],
        (res.json \ "bonus").as[Int],
        (res.json \ "points_per_game").as[BigDecimal],
        (res.json \ "dreamteam_count").as[Int]
      ),
      playerInfo = PlayerInfo(
        (res.json \ "web_name").toString,
        (res.json \ "first_name").toString,
        (res.json \ "second_name"). toString,
        (res.json \ "type_name"). toString,
        (res.json \ "team_name"). toString,
        (res.json \ "news"). toString,
        (res.json \ "status"). toString,
        (res.json \ "photo"). toString,
        (res.json \ "team_code").as[Int],
        (res.json \ "team_id").as[Int],
        (res.json \ "team").as[Int]
      ),
      value = Value(
        (res.json \ "now_cost").as[Int],
        (res.json \ "value_form").as[BigDecimal],
        (res.json \ "value_season").as[BigDecimal],
        (res.json \ "cost_change_start").as[Int],
        (res.json \ "cost_change_event").as[Int],
        (res.json \ "cost_change_start_fall").as[Int],
        (res.json \ "cost_change_event_fall").as[Int]
      ),
      eventInfo = EventInfo(
        (res.json \ "event_points").as[Int],
        (res.json \ "ep_this").asOpt[BigDecimal],
        (res.json \ "ep_next").as[BigDecimal],
        (res.json \ "event_total").as[Int],
        (res.json \ "current_fixture").toString,
        (res.json \ "next_fixture").toString,
        (res.json \ "in_dreamteam").as[Boolean]
      ),
      other = Other(
        (res.json \ "selected_by_percent").as[BigDecimal],
        (res.json \ "form").as[BigDecimal],
        (res.json \ "special").as[Boolean],
        (res.json \ "ea_index").as[Int],
        (res.json \ "bps").as[Int],
        (res.json \ "element_type").as[Int]
      )
    )


  }

  def fetch = Action.async {
    WS.url("http://fantasy.premierleague.com/web/api/elements/2/").get().map { res =>
      val player = construct(res)
      val eventualInt: Future[Int] = PlayerDAO.insert(player)
      Result(
        header = ResponseHeader(200, Map("Content-Type" -> "text/plain")),
        body = Enumerator(res.body.getBytes)
      )
    }
  }


  def mine(id: Int): Boolean = {

    true
  }


}
