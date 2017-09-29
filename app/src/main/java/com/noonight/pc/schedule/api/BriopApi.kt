package com.noonight.pc.schedule.api

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by PC on 9/16/2017.
 */
interface BriopApi {
    @GET("/api.php?table=lessons")
    fun getLessons() : Call<List<Lessons>>

    @GET("/api.php?table=users_type")
    fun getUsers_type() : Call<List<Users_Type>>

    @GET("/api.php?table=users")
    fun getUsers() : Call<List<Users>>

    @GET("/api.php?table=courses")
    fun getCourses() : Call<List<Courses>>

    @GET("/api.php?table=listeners")
    fun getListeners() : Call<List<Listeners>>


}