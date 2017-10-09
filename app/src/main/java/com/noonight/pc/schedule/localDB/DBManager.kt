package com.noonight.pc.schedule.localDB

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import com.noonight.pc.schedule.api.*
import com.noonight.pc.schedule.extensions.loger.Log
import com.orm.SugarDb
import com.orm.SugarRecord


class DBManager {
    companion object {
        var rootActivity: Activity? = null
        var db: SQLiteDatabase? = null
        fun newInstance(activity: Activity): DBManager {
            rootActivity = activity
            db = SugarDb(rootActivity).db
            return DBManager()
        }
    }

    fun getCoursesForUser(id: String) {
        val selectionArgs = arrayOfNulls<String>(1)
        selectionArgs[0] = id

        val sql = "SELECT * FROM COURSES_LOCAL WHERE IDUSERLECTURER = ?"
        val ex = db!!.rawQuery(sql, selectionArgs)

        if (ex.moveToFirst()) {
            var id = ex.getColumnIndex("ID")
            var idcourses = ex.getColumnIndex("IDCOURSES")
            var title = ex.getColumnIndex("TITLE")
            var description = ex.getColumnIndex("DESCRIPTION")
            var iduserlecturer = ex.getColumnIndex("IDUSERLECTURER")
            var startdate = ex.getColumnIndex("STARTDATE")
            var enddate = ex.getColumnIndex("ENDDATE")
            do {
                Log.d("id = ${ex.getString(id)}\nidcourse = ${ex.getString(idcourses)}\n" +
                        "title = ${ex.getString(title)}\ndescription = ${ex.getString(description)}\n" +
                        "iduserlecturer = ${ex.getString(iduserlecturer)}\nstartdate = ${ex.getString(startdate)}" +
                        "\nenddate = ${ex.getString(enddate)}")
            } while (ex.moveToNext())
        } else {
            Log.d("no rows")
        }
    }

    fun deleteAllLocal() {
        SugarRecord.deleteAll(LessonsLocal::class.java)
        SugarRecord.deleteAll(CoursesLocal::class.java)
        SugarRecord.deleteAll(ListenersLocal::class.java)
        SugarRecord.deleteAll(UsersLocal::class.java)
        SugarRecord.deleteAll(Users_TypeLocal::class.java)
    }

    /*fun getLessons(): List<Lessons> = SugarRecord.listAll(Lessons::class.java)
    fun getCourses(): List<Courses> = SugarRecord.listAll(Courses::class.java)
    fun getListeners(): List<Listeners> = SugarRecord.listAll(Listeners::class.java)
    fun getUsers(): List<Users> = SugarRecord.listAll(Users::class.java)
    fun getUsers_Type(): List<Users_Type> = SugarRecord.listAll(Users_Type::class.java)*/

    fun getLessonsLocal(): List<LessonsLocal> = SugarRecord.listAll(LessonsLocal::class.java)
    fun getCoursesLocal(): List<CoursesLocal> = SugarRecord.listAll(CoursesLocal::class.java)
    fun getListenersLocal(): List<ListenersLocal> = SugarRecord.listAll(ListenersLocal::class.java)
    fun getUsersLocal(): List<UsersLocal> = SugarRecord.listAll(UsersLocal::class.java)
    fun getUsers_TypeLocal(): List<Users_TypeLocal> = SugarRecord.listAll(Users_TypeLocal::class.java)
}