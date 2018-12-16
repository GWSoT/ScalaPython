package com.eleks.playhttp.database

import java.sql.{Date, Time}

import akka.http.scaladsl.model.DateTime
import com.eleks.playhttp.core.BaseDatabase
import com.eleks.playhttp.models.{Movie, Schedule}
import com.eleks.playhttp.models.dto.ScheduleResponse
import java.math.BigDecimal
class ScheduleTable extends BaseDatabase[Schedule, Long]("Schedule") {

  override def insert(row: Schedule): Schedule = {
    val statement = connection.createStatement
    val command = s"INSERT INTO Schedule (Time, MovieId, Price, HallId) VALUES " +
      s"('${row.time}','${row.movieId}','${row.price}','${row.hallId}')"
      statement.execute(command)
    connection.commit()
    row
  }

  def getScheduleById(id: Long) = {
    val statement = connection.createStatement
    val query = s"SELECT m.Id as MovieId, m.Genre as Genre, m.Name as Name, " +
      s"s.Id as Id, s.Time as Time, s.Price as Price, s.HallId as HallId" +
      s" FROM Schedule s JOIN Movie m on m.Id = s.MovieId WHERE Id = $id"
    val resultSet = statement.executeQuery(query)
    super.realize(resultSet).map(f => {
      ScheduleResponse(f("Id").asInstanceOf[Int],
        String.valueOf(f("Time")),
        Movie(f("MovieId").asInstanceOf[Int],
          f("Name").toString,
          f("Genre").toString),
        f("Price").toString,
        f("HallId").asInstanceOf[Int]
      )
    }).toList
  }

  def getAllSchedules: List[ScheduleResponse] = {
    val statement = connection.createStatement
    val query = s"SELECT m.Id as MovieId, m.Genre as Genre, Name as Name, " +
      s"s.Id as Id, Time as Time, Price as Price, HallId as HallId" +
      s" FROM Schedule s JOIN Movie m on m.Id = s.MovieId"
    val resultSet = statement.executeQuery(query)
    super.realize(resultSet).map(f => {
      ScheduleResponse(f("Id").asInstanceOf[Int],
        String.valueOf(f("Time")),
        Movie(f("MovieId").asInstanceOf[Int],
          f("Name").toString,
          f("Genre").toString),
        f("Price").toString,
        f("HallId").asInstanceOf[Int]
      )
    }).toList
  }

  def getAllByGenre(genre: String): List[ScheduleResponse] = {
    val statement = connection.createStatement
    val query = s"SELECT m.Id as MovieId, m.Genre as Genre, m.Name as Name, " +
      s"s.Id as Id, s.Time as Time, s.Price as Price, s.HallId as HallId" +
      s" FROM Schedule s JOIN Movie m on m.Id = s.MovieId WHERE Genre = '$genre'"
    val resultSet = statement.executeQuery(query)
    connection.commit()
    super.realize(resultSet).map(f => {
      ScheduleResponse(f("Id").asInstanceOf[Int],
        String.valueOf(f("Time")),
        Movie(f("MovieId").asInstanceOf[Int],
          f("Name").toString,
          f("Genre").toString),
        f("Price").toString,
        f("HallId").asInstanceOf[Int]
      )
    }).toList

  }
}