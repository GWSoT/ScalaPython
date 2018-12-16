package com.eleks.playhttp.repository

import com.eleks.playhttp.core.BaseRepository
import com.eleks.playhttp.database.ScheduleTable
import com.eleks.playhttp.models.Schedule
import com.eleks.playhttp.models.dto.ScheduleResponse
import javax.inject.{Inject, Scope}

@Scope
class ScheduleRepository @Inject() (scheduleTable: ScheduleTable)
  extends BaseRepository[ScheduleTable, Schedule, Long](scheduleTable) {

  def getScheduleById(id: Long): Option[ScheduleResponse] = {
    var scheduleList = scheduleTable.getScheduleById(id)
    scheduleList.headOption
  }

  def getAllSchedules: List[ScheduleResponse] = scheduleTable.getAllSchedules

  def getByGenre(genre: String): List[ScheduleResponse] = scheduleTable.getAllByGenre(genre)

}
