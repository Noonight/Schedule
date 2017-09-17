package com.noonight.pc.schedule.api

class Lessons(
        val id_lesson: Int,
        val day: String,
        val start_time: String,
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
        val start_date: String,
        val end_date: String,
        val id_user_lecturer: Int
)

class Listeners(
        val id_listener: Int,
        val id_user: Int,
        val id_courses: Int
)