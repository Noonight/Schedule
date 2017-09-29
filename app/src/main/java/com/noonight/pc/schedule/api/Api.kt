package com.noonight.pc.schedule.api

import retrofit2.Call

/**
 * Created by PC on 9/16/2017.
 */
interface Api {
    fun getLessons() : Call<List<Lessons>>
    fun getUsers_Type() : Call<List<Users_Type>>
    fun getUsers() : Call<List<Users>>
    fun getCourses() : Call<List<Courses>>
    fun getListeners() : Call<List<Listeners>>
}