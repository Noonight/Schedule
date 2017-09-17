package com.noonight.pc.schedule.schedules.lessons

import com.noonight.pc.schedule.api.ScheduleRestApi
import com.noonight.pc.schedule.schedules.Lessons
import com.noonight.pc.schedule.schedules.LessonsItem
import rx.Observable

/**
 * Created by PC on 9/17/2017.
 */
class ScheduleManager(private val api: ScheduleRestApi = ScheduleRestApi()) {
    fun getLessons(): Observable<Lessons> {
        return Observable.create {
            subscribe ->
            val callResponse = api.getLessons()
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body()
                val lessons = dataResponse?.map {
                    val item = it
                    LessonsItem(
                            item.id_lesson,
                            item.day,
                            item.start_time,
                            item.long_time,
                            item.id_courses
                    )
                }
                val briopLessons = Lessons(lessons!!)
                subscribe.onNext(briopLessons)
                subscribe.onCompleted()
            } else {
                subscribe.onError(Throwable(response.message()))
            }
        }
    }
}