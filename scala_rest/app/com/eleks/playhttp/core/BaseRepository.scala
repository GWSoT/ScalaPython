package com.eleks.playhttp.core

import java.sql.ResultSet

import scala.reflect.ClassTag

abstract class BaseRepository[TTable <: BaseDatabase[TType, KType], TType <: BaseEntity[KType], KType : ClassTag]
  (table: BaseDatabase[TType, KType]) {

  def insert(row: TType) = table.insert(row)

  def getById(id: Long): ResultSet = table.getById(id)

  def getAll: ResultSet = table.getAll

  def deleteById(id: Long) = table.delete(id)
}
