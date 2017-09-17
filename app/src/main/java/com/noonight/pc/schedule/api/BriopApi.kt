package com.noonight.pc.schedule.api

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by PC on 9/16/2017.
 */
interface BriopApi {
    @GET("/api.php")
    fun getLessons() : Call<Lessons>

    @GET("/api.php")
    fun getUsers_type() : Call<Users_Type>

    @GET("/api.php")
    fun getUsers() : Call<Users>

    @GET("/api.php")
    fun getCourses() : Call<Courses>

    @GET("/api.php")
    fun getListeners() : Call<Listeners>


}