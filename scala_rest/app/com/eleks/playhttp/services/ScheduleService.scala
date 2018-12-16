package com.eleks.playhttp.services

import com.eleks.playhttp.database.ScheduleTable
import com.eleks.playhttp.models.{Movie, Schedule}
import com.eleks.playhttp.models.dto.ScheduleResponse
import com.eleks.playhttp.repository.{MovieRepository, ScheduleRepository}
import javax.inject.{Inject, Scope}

@Scope
class ScheduleService @Inject() (scheduleRepo: ScheduleRepository, movieRepo: MovieRepository) {

  def getAll = scheduleRepo.getAllSchedules

  def getByGenre(genre: String): List[ScheduleResponse] = scheduleRepo.getByGenre(genre)

  def addSchedule(schedule: Schedule) = scheduleRepo.insert(schedule)

  def deleteScheduleById(id: Long) = scheduleRepo.deleteById(id)
}
