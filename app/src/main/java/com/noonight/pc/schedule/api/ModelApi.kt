package com.noonight.pc.schedule.api

import android.os.Parcel
import android.os.Parcelable
import com.noonight.pc.schedule.schedules.lessons.adapter.LessonInterface
import com.orm.SugarRecord

/*data class Lessons(
        val id_lesson: Int,
        val day: String,
        val start_time: String,
        val long_time: Int,
        val id_courses: Int
)  : LessonInterface, SugarRecord<Lessons>()*/

data class Lessons(
        val id_lesson: Int = 0,
        val day: String = "",
        val start_time: String = "",
        val long_time: Int = 0,
        val id_courses: Int = 0
)  : LessonInterface, SugarRecord()

/*data class Lessons(
        var id_lesson: Int,
        var day: String,
        var start_time: String,
        var long_time: Int,
        var id_courses: Int
)  : LessonInterface, SugarRecord<Lessons>()*/

data class Users_Type(
        val id_user_type: Int,
        val user_type: String
) : SugarRecord()

data class Users(
        val id_user: Int,
        val name: String,
        val id_user_type: Int
) : SugarRecord()

data class Courses(
        val id_courses: Int,
        val title: String,
        val description: String,
        val start_date: String,
        val end_date: String,
        val id_user_lecturer: Int
) : SugarRecord()

data class Listeners(
        val id_listener: Int,
        val id_user: Int,
        val id_courses: Int
) : SugarRecord()