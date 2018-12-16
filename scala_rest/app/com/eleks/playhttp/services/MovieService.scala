package com.eleks.playhttp.services

import com.eleks.playhttp.database.MovieTable
import com.eleks.playhttp.models.Movie
import com.eleks.playhttp.repository.MovieRepository
import javax.inject.{Inject, Scope}
import play.api.libs.json.JsValue

@Scope
class MovieService @Inject() (movieRepo: MovieRepository) {

  def getMovie(id: Long) = movieRepo.getMovieById(id)

  def addMovie(movie: Movie) = movieRepo.insert(movie)

  def deleteMovie(id: Long) = movieRepo.deleteById(id)

  def getAll: List[Movie] = movieRepo.getAllMovies

  def getMoviesByIds(ids: List[Long]) = getAll.filter(f => ids.contains(f.id))

  def getMoviesByGenre(genre: String) = getAll.filter(f => f.genre == genre)
}
