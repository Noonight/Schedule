package com.noonight.pc.schedule.localDB

import com.noonight.pc.schedule.api.*
import com.noonight.pc.schedule.extensions.loger.Log
import com.orm.SugarRecord


class DBManager {

    fun deleteAllLocal() {
        SugarRecord.deleteAll(LessonsLocal::class.java)
        SugarRecord.deleteAll(CoursesLocal::class.java)
        SugarRecord.deleteAll(ListenersLocal::class.java)
        SugarRecord.deleteAll(UsersLocal::class.java)
        SugarRecord.deleteAll(Users_TypeLocal::class.java)
    }

    fun getLessons(): List<Lessons> = SugarRecord.listAll(Lessons::class.java)
    fun getCourses(): List<Courses> = SugarRecord.listAll(Courses::class.java)
    fun getListeners(): List<Listeners> = SugarRecord.listAll(Listeners::class.java)
    fun getUsers(): List<Users> = SugarRecord.listAll(Users::class.java)
    fun getUsers_Type(): List<Users_Type> = SugarRecord.listAll(Users_Type::class.java)

    fun getLessonsLocal(): List<LessonsLocal> = SugarRecord.listAll(LessonsLocal::class.java)
    fun getCoursesLocal(): List<CoursesLocal> = SugarRecord.listAll(CoursesLocal::class.java)
    fun getListenersLocal(): List<ListenersLocal> = SugarRecord.listAll(ListenersLocal::class.java)
    fun getUsersLocal(): List<UsersLocal> = SugarRecord.listAll(UsersLocal::class.java)
    fun getUsers_TypeLocal(): List<Users_TypeLocal> = SugarRecord.listAll(Users_TypeLocal::class.java)
}