package com.noonight.pc.schedule.localDB

import com.noonight.pc.schedule.api.*
import com.orm.SugarRecord


class DBManager {



    fun getLessons(): List<Lessons> = SugarRecord.listAll(Lessons::class.java)
    fun getCourses(): List<Courses> = SugarRecord.listAll(Courses::class.java)
    fun getListeners(): List<Listeners> = SugarRecord.listAll(Listeners::class.java)
    fun getUsers(): List<Users> = SugarRecord.listAll(Users::class.java)
    fun getUsers_Type(): List<Users_Type> = SugarRecord.listAll(Users_Type::class.java)
}