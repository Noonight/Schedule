package com.noonight.pc.schedule.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by PC on 9/16/2017.
 */
class ScheduleRestApi : ScheduleApi {

    private val brioApi: BriopApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("10.0.2.2")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        brioApi = retrofit.create(BriopApi::class.java)
    }

    override fun getLessons(): Call<Lessons> {
        return brioApi.getLessons()
    }

    override fun getUsers_Type(): Call<Users_Type> {
        return brioApi.getUsers_type()
    }

    override fun getUsers(): Call<Users> {
        return brioApi.getUsers()
    }

    override fun getCourses(): Call<Courses> {
        return brioApi.getCourses()
    }

    override fun getListeners(): Call<Listeners> {
        return brioApi.getListeners()
    }
}