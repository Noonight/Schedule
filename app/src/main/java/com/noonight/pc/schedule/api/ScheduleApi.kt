package com.noonight.pc.schedule.api

import retrofit2.Call

/**
 * Created by PC on 9/16/2017.
 */
interface ScheduleApi {
    fun getLessons() : Call<Lessons>
    fun getUsers_Type() : Call<Users_Type>
    fun getUsers() : Call<Users>
    fun getCourses() : Call<Courses>
    fun getListeners() : Call<Listeners>
}