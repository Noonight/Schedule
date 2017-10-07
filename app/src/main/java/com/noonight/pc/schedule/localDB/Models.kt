package com.noonight.pc.schedule.localDB

import com.noonight.pc.schedule.schedules.adapter.LessonInterface
import com.orm.SugarRecord

data class LessonsLocal(
        val id_lesson: Int = -1,
        val day: String = "",
        val start_time: String = "",
        val auditory: String = "",
        val type: String = "",
        val id_courses: Int = -1,
        val cours: CoursesLocal? = null
)  : SugarRecord(), LessonInterface

data class Users_TypeLocal(
        val id_user_type: Int = -1,
        val user_type: String = ""
) : SugarRecord()

data class UsersLocal(
        val id_user: Int = -1,
        val name: String = "",
        val id_user_type: Int = -1,
        val user_type: Users_TypeLocal? = null
) : SugarRecord()

data class CoursesLocal(
        val id_courses: Int = -1,
        val title: String = "",
        val description: String = "",
        val start_date: String = "",
        val end_date: String = "",
        val id_user_lecturer: Int = -1,
        val user_lecturer: UsersLocal? = null
) : SugarRecord()

data class ListenersLocal(
        val id_listener: Int = -1,
        val id_user: Int = -1,
        val user: UsersLocal? = null,
        val id_courses: Int = -1,
        val cours: CoursesLocal? = null
) : SugarRecord()