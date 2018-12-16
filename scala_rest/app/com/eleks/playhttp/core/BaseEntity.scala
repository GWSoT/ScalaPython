package com.eleks.playhttp.core

import scala.reflect.ClassTag

abstract class BaseEntity[F : ClassTag] {
  val id: F
}
