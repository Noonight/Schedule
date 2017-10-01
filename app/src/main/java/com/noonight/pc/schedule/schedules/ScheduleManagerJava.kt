package com.noonight.pc.schedule.schedules

import com.noonight.pc.schedule.api.Lessons
import com.noonight.pc.schedule.api.RestApi
import com.noonight.pc.schedule.loger.Log

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ScheduleManagerJava {
    private val api = RestApi()

    val lessonsLocal = lessons

    val lessons: List<Lessons>
        get() {
            val lessons = ArrayList<Lessons>()
            api.getLessons().enqueue(object : Callback<List<Lessons>> {
                override fun onResponse(call: Call<List<Lessons>>, response: Response<List<Lessons>>) {
                    lessons.addAll(response.body())
                    Log.d(response.body().toString())
                }

                override fun onFailure(call: Call<List<Lessons>>, t: Throwable) {

                }
            })
            return lessons
        }
}
