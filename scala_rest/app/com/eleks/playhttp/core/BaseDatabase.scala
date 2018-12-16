package com.eleks.playhttp.core

import java.sql.{DriverManager, ResultSet}

import scala.reflect.ClassTag

abstract class BaseDatabase[T <: BaseEntity[F], F : ClassTag](table: String) {

  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
  var connectionString = "jdbc:sqlserver://localhost;database=Cinema;user=Test;password=123123;loginTimeout=30"
  val connection = DriverManager.getConnection(connectionString)

  def getById(id: Long) = {
    val statement = connection.createStatement
    val command = s"SELECT * FROM $table WHERE Id = $id"
    val resultSet = statement.executeQuery(command)
    connection.commit()
    resultSet
  }

  def getAll: ResultSet = {
    val connection = DriverManager.getConnection(connectionString)
    val statement = connection.createStatement
    val command = s"SELECT * FROM $table"
    val resultSet = statement.executeQuery(command)
    connection.commit()
    resultSet
  }

  def insert(row: T): T

  def delete(id: Long) = {
    val connection = DriverManager.getConnection(connectionString)
    val statement = connection.createStatement
    val command = s"DELETE FROM $table WHERE Id = $id"
    statement.execute(command)
    connection.commit()
  }

  protected def buildMap(queryResult: ResultSet, colNames: Seq[String]): Option[Map[String, Object]] = {
    if (queryResult.next())
      Some(colNames.map(n => n -> queryResult.getObject(n)).toMap)
    else
      None
  }

  protected def realize(queryReult: ResultSet): Vector[Map[String, Object]] = {
    val md = queryReult.getMetaData
    val colNames = (1 to md.getColumnCount) map md.getColumnName
    Iterator.continually(buildMap(queryReult, colNames)).takeWhile(_.isDefined).map(_.get).toVector
  }

  def saveChanges = connection.commit()
}
