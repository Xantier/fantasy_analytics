package controllers

import play.mvc.Controller
import services.PlayerService

class Fetcher extends Controller {

  def fetch = PlayerService.fetch

  def player(name:String) = PlayerService.load(name)
}

object Fetcher extends Fetcher()