package controllers

import play.mvc.Controller
import services.PlayerService

class Fetcher extends Controller {

  def fetch = PlayerService.fetch
}

object Fetcher extends Fetcher()