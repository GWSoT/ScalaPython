package com.eleks.playhttp.models.dto

import akka.http.scaladsl.model.DateTime
import com.eleks.playhttp.core.BaseEntity
import com.eleks.playhttp.models.Movie
import java.math.BigDecimal

case class ScheduleResponse(id: Long = 0, datetime: String, movie: Movie, price: String, hallId: Long)
  extends BaseEntity[Long]
