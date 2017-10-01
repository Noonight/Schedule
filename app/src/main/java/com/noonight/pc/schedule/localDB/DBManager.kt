package com.noonight.pc.schedule.localDB

import com.noonight.pc.schedule.api.*
import com.noonight.pc.schedule.loger.Log
import com.orm.SugarRecord
import java.util.*


class DBManager {

    fun createRelationships() {
        for (i in 0 until getLessons().size) {

        }
    }

    fun deleteAllLocal() {
        SugarRecord.deleteAll(LessonsLocal::class.java)
        SugarRecord.deleteAll(CoursesLocal::class.java)
        SugarRecord.deleteAll(ListenersLocal::class.java)
        SugarRecord.deleteAll(UsersLocal::class.java)
        SugarRecord.deleteAll(Users_TypeLocal::class.java)
    }

    fun createLocalTables() {
        val array = mutableListOf<Long>()
        array.add(LessonsLocal().save())
        array.add(CoursesLocal().save())
        array.add(ListenersLocal().save())
        array.add(UsersLocal().save())
        array.add(Users_TypeLocal().save())
        for (i in array)
            Log.d(i.toString())
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