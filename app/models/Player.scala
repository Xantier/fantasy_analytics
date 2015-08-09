package models

import slick.lifted.Tag
import slick.driver.PostgresDriver.api._


case class Player(
  id: Option[Long],
  code: Int,
  chanceOfPlayingThisRound: Option[Int],
  chanceOfPlayingNextRound: Option[Int],
  transfers: Transfers,
  points: Points,
  playerInfo: PlayerInfo,
  value: Value,
  eventInfo: EventInfo,
  other: Other
)

case class Other(
  selectedByPercent: BigDecimal,
  form: BigDecimal,
  special: Boolean,
  eaIndex: Int,
  bps: Int,
  elementType: Int
)

case class EventInfo(
  eventPoints: Int,
  epThis: Option[BigDecimal],
  epNext: BigDecimal,
  eventTotal: Int,
  currentFixture: String,
  nextFixture: String,
  inDreamteam: Boolean
)

case class Value(
  nowCost: Int,
  valueForm: BigDecimal,
  valueSeason: BigDecimal,
  costChangeStart: Int,
  costChangeEvent: Int,
  costChangeStartFall: Int,
  costChangeEventFall: Int
)

case class PlayerInfo(
  shortName: String,
  firstName: String,
  lastName: String,
  position: String,
  teamName: String,
  news: String,
  status: String,
  photo: String,
  teamCode: Int,
  teamId: Int,
  team: Int
)

case class Transfers(
  transfersOut: Int,
  transfersIn: Int,
  transfersOutEvent: Int,
  transfersInEvent: Int,
  loansIn: Int,
  loansOut: Int,
  loanedIn: Int,
  loanedOut: Int
)

case class Points(
  totalPoints: Int,
  minutes: Int,
  goalsScored: Int,
  assists: Int,
  cleanSheets: Int,
  goalsConceded: Int,
  ownGoals: Int,
  penaltiesSaved: Int,
  penaltiesMissed: Int,
  yellowCards: Int,
  redCards: Int,
  saves: Int,
  bonus: Int,
  pointsPerGame: BigDecimal,
  dreamteamCount: Int
)

class Players(tag: Tag) extends Table[Player](tag, "players") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def photo = column[String]("photo")

  def shortName = column[String]("shortName")

  def eventTotal = column[Int]("eventTotal")

  def position = column[String]("position")

  def teamName = column[String]("teamName")

  def totalPoints = column[Int]("totalPoints")

  def currentFixture = column[String]("currentFixture")

  def nextFixture = column[String]("nextFixture")

  def teamCode = column[Int]("teamCode")

  def news = column[String]("news")

  def teamId = column[Int]("teamId")

  def status = column[String]("status")

  def code = column[Int]("code")

  def firstName = column[String]("firstName")

  def lastName = column[String]("lastName")

  def nowCost = column[Int]("nowCost")

  def chanceOfPlayingThisRound = column[Int]("chanceOfPlayingThisRound")

  def chanceOfPlayingNextRound = column[Int]("chanceOfPlayingNextRound")

  def valueForm = column[BigDecimal]("valueForm")

  def valueSeason = column[BigDecimal]("valueSeason")

  def costChangeStart = column[Int]("costChangeStart")

  def costChangeEvent = column[Int]("costChangeEvent")

  def costChangeStartFall = column[Int]("costChangeStartFall")

  def costChangeEventFall = column[Int]("costChangeEventFall")

  def inDreamteam = column[Boolean]("inDreamteam")

  def dreamteamCount = column[Int]("dreamteamCount")

  def selectedByPercent = column[BigDecimal]("selectedByPercent")

  def form = column[BigDecimal]("form")

  def transfersOut = column[Int]("transfersOut")

  def transfersIn = column[Int]("transfersIn")

  def transfersOutEvent = column[Int]("transfersOutEvent")

  def transfersInEvent = column[Int]("transfersInEvent")

  def loansIn = column[Int]("loansIn")

  def loansOut = column[Int]("loansOut")

  def loanedIn = column[Int]("loanedIn")

  def loanedOut = column[Int]("loanedOut")

  def eventPoints = column[Int]("eventPoints")

  def pointsPerGame = column[BigDecimal]("pointsPerGame")

  def epThis = column[BigDecimal]("epThis")

  def epNext = column[BigDecimal]("epNext")

  def special = column[Boolean]("special")

  def minutes = column[Int]("minutes")

  def goalsScored = column[Int]("goalsScored")

  def assists = column[Int]("assists")

  def cleanSheets = column[Int]("cleanSheets")

  def goalsConceded = column[Int]("goalsConceded")

  def ownGoals = column[Int]("ownGoals")

  def penaltiesSaved = column[Int]("penaltiesSaved")

  def penaltiesMissed = column[Int]("penaltiesMissed")

  def yellowCards = column[Int]("yellowCards")

  def redCards = column[Int]("redCards")

  def saves = column[Int]("saves")

  def bonus = column[Int]("bonus")

  def eaIndex = column[Int]("eaIndex")

  def bps = column[Int]("bps")

  def elementType = column[Int]("elementType")

  def team = column[Int]("team")

  def * = (id.?, code, chanceOfPlayingThisRound.?, chanceOfPlayingNextRound.?, transfers, points, playerInfo, value, eventInfo, other) <> (Player.tupled, Player.unapply)

  def transfers = (transfersOut, transfersIn, transfersOutEvent, transfersInEvent, loansIn, loansOut, loanedIn, loanedOut) <> (Transfers.tupled, Transfers.unapply)

  def points = (totalPoints, minutes, goalsScored, assists, cleanSheets, goalsConceded, ownGoals, penaltiesSaved, penaltiesMissed, yellowCards, redCards, saves, bonus, pointsPerGame, dreamteamCount) <> (Points.tupled, Points.unapply)

  def playerInfo = (shortName, firstName, lastName, position, teamName, news, status, photo, teamCode, teamId, team) <> (PlayerInfo.tupled, PlayerInfo.unapply)

  def value = (nowCost, valueForm, valueSeason, costChangeStart, costChangeEvent, costChangeStartFall, costChangeEventFall) <> (Value.tupled, Value.unapply)

  def eventInfo = (eventPoints, epThis.?, epNext, eventTotal, currentFixture, nextFixture, inDreamteam) <> (EventInfo.tupled, EventInfo.unapply)

  def other = (selectedByPercent, form, special, eaIndex, bps, elementType) <> (Other.tupled, Other.unapply)
}
