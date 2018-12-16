package com.eleks.playhttp.models

import akka.http.scaladsl.model.DateTime
import java.math.BigDecimal
import com.eleks.playhttp.core.BaseEntity

case class Schedule (id: Long, time: String, movieId: Long, price: Double, hallId: Long) extends BaseEntity[Long]
