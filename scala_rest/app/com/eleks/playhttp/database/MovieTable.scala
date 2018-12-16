package com.eleks.playhttp.database

import java.sql.{Connection, DriverManager, ResultSet}

import com.eleks.playhttp.core.BaseDatabase
import com.eleks.playhttp.models.Movie
import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsObject, Json}
import play.api.libs.json._

@Singleton
class MovieTable extends BaseDatabase[Movie, Long]("Movie") {

  implicit val movieJson = Json.format[Movie]

  override def insert(row: Movie): Movie = {
    val statement = connection.createStatement
    val command = s"INSERT INTO Movie (Name, Genre) VALUES ('${row.name}', '${row.genre}')"
    statement.execute(command)
    connection.commit()
    row
  }

  def getMovieById(id: Long): Option[Movie] = {
    val resultSet = super.getById(id)
    resultSet.next()
    Some(Movie(resultSet.getLong(1),
      resultSet.getString(2),
      resultSet.getString(3)))
  }

  def getAllMovies: List[Movie] = {
    val movies = super.getAll
    super.realize(movies).map(f => {
      Movie(f("Id").asInstanceOf[Int],
        String.valueOf(f("Name")),
        String.valueOf(f("Genre")))
    }).toList
  }
}
