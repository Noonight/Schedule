package com.noonight.pc.schedule.api

import java.sql.Time
import java.util.*

/**
 * Created by PC on 9/16/2017.
 */

class Lessons(
        val id_lesson: Int,
        val day: Date,
        val start_time: Time,
        val long_time: Int,
        val id_courses: Int
)

class Users_Type(
        val id_user_type: Int,
        val user_type: String
)

class Users(
        val id_user: Int,
        val name: String,
        val id_user_type: Int
)

class Courses(
        val id_courses: Int,
        val title: String,
        val description: String,
        val start_date: Date,
        val end_date: Date,
        val id_user_lecturer: Int
)

class Listeners(
        val id_listener: Int,
        val id_user: Int,
        val id_courses: Int
)