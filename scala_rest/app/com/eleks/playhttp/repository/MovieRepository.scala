package com.eleks.playhttp.repository

import com.eleks.playhttp.core.BaseRepository
import com.eleks.playhttp.database.{MovieTable, ScheduleTable}
import com.eleks.playhttp.models.{Movie, Schedule}
import javax.inject.{Inject, Scope}

@Scope
class MovieRepository @Inject() (movieTable: MovieTable)
  extends BaseRepository[MovieTable, Movie, Long](movieTable) {
  def getMovieById(id: Long): Option[Movie] = movieTable.getMovieById(id)
  def getAllMovies: List[Movie] = movieTable.getAllMovies
}
