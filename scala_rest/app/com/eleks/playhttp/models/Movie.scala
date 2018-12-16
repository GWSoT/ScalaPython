package com.eleks.playhttp.models

import com.eleks.playhttp.core.BaseEntity

case class Movie(id: Long = 0, name: String, genre: String) extends BaseEntity[Long]