package com.eleks.playhttp.controllers

import com.eleks.playhttp.models.{Movie, Schedule}
import com.eleks.playhttp.models.dto.ScheduleResponse
import com.eleks.playhttp.services.ScheduleService
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, InjectedController}

class ScheduleController @Inject() (scheduleService: ScheduleService) extends InjectedController {

  implicit val movieForamt = Json.format[Movie]
  implicit val scheduleFormat = Json.format[ScheduleResponse]
  implicit val scheduleResponseFormat = Json.format[Schedule]

  def getAll(genre: String) = Action {
    if (!genre.isEmpty)
      Ok(Json.obj("Schedules" -> scheduleService.getByGenre(genre)))
    else
      Ok(Json.obj("Schedules" -> scheduleService.getAll))
  }

  def addSchedule: Action[JsValue] = Action(parse.json) { request =>
    val schedule = request.body.as[Schedule]
    Ok(Json.toJson(scheduleService.addSchedule(schedule)))
  }

  def deleteSchedule(id: Long) = Action {
    scheduleService.deleteScheduleById(id)
    Ok("Success")
  }
}
