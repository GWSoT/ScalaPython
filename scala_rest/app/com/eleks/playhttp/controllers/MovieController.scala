package com.eleks.playhttp.controllers

import com.eleks.playhttp.models.Movie
import com.eleks.playhttp.services.MovieService
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.InjectedController

class MovieController @Inject()(movieService: MovieService) extends InjectedController {

  implicit val movieFormat = Json.format[Movie]

  def getAll = Action {
    val movies = movieService.getAll
    if (movies.isEmpty)
      NotFound("Movies is empty")
    Ok(Json.obj("Movies" -> movieService.getAll))
  }

  def getMovie(id: Long) = Action {
    val movieOpt = movieService.getMovie(id)
    movieOpt match {
      case Some(movie) => Ok(Json.toJson(movie))
      case None => NotFound(s"Movie with id $id not found ")
    }
  }

  def addMovie = Action(parse.json) { request =>
    val movie = request.body.as[Movie]
    Ok(Json.toJson(movieService.addMovie(movie)))
  }

  def deleteMovie(id: Long) = {
    movieService.deleteMovie(id)
  }
}
