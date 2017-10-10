package com.noonight.pc.schedule.localDB

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.noonight.pc.schedule.api.*
import com.noonight.pc.schedule.extensions.loger.Log
import com.orm.SugarDb
import com.orm.SugarRecord
import java.sql.Date


class DBManager {
    companion object {
        var context: Context? = null
        var db: SQLiteDatabase? = null
        fun newInstance(context: Context): DBManager {
            this.context = context
            db = SugarDb(this.context).db
            return DBManager()
        }
    }

    fun getUserByName(name: String): UsersLocal? {
        return SugarRecord.find(UsersLocal::class.java, "name = ?", name).get(0)
    }

    fun getLessonsForUser(id: String) {
        val selectionArgs = arrayOfNulls<String>(1)
        selectionArgs[0] = id

        val sql = "SELECT l.IDLESSON AS IDLESSON, l.IDCOURSES AS IDCOURSES, l.STARTTIME AS STARTTIME, " +
                "l.DAY AS DAY, l.AUDITORY AS AUDITORY, l.TYPE AS TYPE\n" +
                "FROM LESSONS_LOCAL as l,\n" +
                "  (SELECT tab.IDCOURSES\n" +
                "   FROM COURSES_LOCAL AS cl,\n" +
                "     (SELECT ll.IDCOURSES\n" +
                "      FROM USERS_LOCAL AS ul INNER JOIN LISTENERS_LOCAL AS ll\n" +
                "          ON ul.IDUSER = ll.IDUSER\n" +
                "      WHERE ul.IDUSER = ?\n" +
                "      ORDER BY ll.IDCOURSES) AS tab\n" +
                "   WHERE tab.IDCOURSES = cl.IDCOURSES) AS tg\n" +
                "WHERE l.IDCOURSES = tg.IDCOURSES"
        val ex = db!!.rawQuery(sql, selectionArgs)

        if (ex.moveToFirst()) {
            //val count: Int = ex.columnCount
            val row = ex.columnNames
            val array = ArrayList<String>()
            array.addAll(row)
            val id_lesson = ex.getColumnIndex("IDLESSON")
            val id_courses = ex.getColumnIndex("IDCOURSES")
            val start_time = ex.getColumnIndex("STARTTIME")
            val day = ex.getColumnIndex("DAY")
            val auditory = ex.getColumnIndex("AUDITORY")
            val type = ex.getColumnIndex("TYPE")
            do {
                val stringB = StringBuilder()
                stringB.append(ex.getString(id_lesson) + " ")
                stringB.append(ex.getString(id_courses)+" ")
                stringB.append(ex.getString(start_time)+" ")
                stringB.append(ex.getString(day)+" ")
                stringB.append(ex.getString(auditory)+" ")
                stringB.append(ex.getString(type)+" ")
                Log.d(stringB.toString())
            } while (ex.moveToNext())
        } else {
            Log.d("no rows")
        }
    }

    fun getCountDayLessonsForUser(id: String): Int {
        var returnedCount: Int = -1
        val selectionArgs = arrayOf(id)
        val sql = "SELECT  COUNT( DISTINCT l.DAY) as count\n" +
                "FROM LESSONS_LOCAL as l,\n" +
                "  (\n" +
                "    SELECT tab.IDCOURSES\n" +
                "      FROM COURSES_LOCAL as c,\n" +
                "      (\n" +
                "        SELECT ll.IDCOURSES\n" +
                "          FROM USERS_LOCAL as u INNER JOIN LISTENERS_LOCAL as ll\n" +
                "            ON u.IDUSER = ll.IDUSER\n" +
                "          WHERE u.IDUSER = ?\n" +
                "          ORDER BY ll.IDCOURSES\n" +
                "      ) AS tab\n" +
                "    WHERE tab.IDCOURSES = c.IDCOURSES\n" +
                "  ) AS tg\n" +
                "WHERE l.IDCOURSES = tg.IDCOURSES"
        val ex = db!!.rawQuery(sql, selectionArgs)
        if (ex.moveToFirst()) {
            val count = ex.getColumnIndex("count")
            do {
                //Log.d(ex.getString(count))
                returnedCount = Integer.parseInt(ex.getString(count))
            } while (ex.moveToNext())
        } else {
            Log.d("not found rows")
        }
        return returnedCount
    }

    fun getArrayDayLessonsForUser(id: String): List<Date> {
        val returned = mutableListOf<Date>()
        val selectionArgs = arrayOf(id)
        val sql = "SELECT  DISTINCT l.DAY as days\n" +
                "FROM LESSONS_LOCAL as l,\n" +
                "  (\n" +
                "    SELECT tab.IDCOURSES\n" +
                "      FROM COURSES_LOCAL as c,\n" +
                "      (\n" +
                "        SELECT ll.IDCOURSES\n" +
                "          FROM USERS_LOCAL as u INNER JOIN LISTENERS_LOCAL as ll\n" +
                "            ON u.IDUSER = ll.IDUSER\n" +
                "          WHERE u.IDUSER = ?\n" +
                "          ORDER BY ll.IDCOURSES\n" +
                "      ) AS tab\n" +
                "    WHERE tab.IDCOURSES = c.IDCOURSES\n" +
                "  ) AS tg\n" +
                "WHERE l.IDCOURSES = tg.IDCOURSES"
        val ex = db!!.rawQuery(sql, selectionArgs)
        if (ex.moveToFirst()) {
            val day = ex.getColumnIndex("days")
            do {
                returned.add(Date.valueOf(ex.getString(day)))
            } while (ex.moveToNext())
        } else {
            Log.d("not found rows")
        }
        return returned
    }

    fun getArrayLessonsAtDayAndForUser(id: String, day: String): MutableList<LessonsLocal> {
        val returned = mutableListOf<LessonsLocal>()
        val selectionArgs = arrayOf(id, day)
        val sql = "SELECT  tg.IDCOURSES as idcours, tg.TITLE as title, tg.DESCRIPTION as description,\n" +
                "  tg.IDUSERLECTURER as idlecturer, l.STARTTIME as starttime, l.TYPE as type,\n" +
                "  l.AUDITORY as auditory, l.DAY as day\n" +
                "FROM LESSONS_LOCAL as l,\n" +
                "  (\n" +
                "    SELECT tab.IDCOURSES, c.TITLE, c.DESCRIPTION, c.IDUSERLECTURER\n" +
                "      FROM COURSES_LOCAL as c,\n" +
                "      (\n" +
                "        SELECT ll.IDCOURSES\n" +
                "          FROM USERS_LOCAL as u INNER JOIN LISTENERS_LOCAL as ll\n" +
                "            ON u.IDUSER = ll.IDUSER\n" +
                "          WHERE u.IDUSER = ?\n" +
                "          ORDER BY ll.IDCOURSES\n" +
                "      ) AS tab\n" +
                "    WHERE tab.IDCOURSES = c.IDCOURSES\n" +
                "  ) AS tg\n" +
                "WHERE l.IDCOURSES = tg.IDCOURSES\n" +
                "AND l.DAY = DATE (?)"
        val ex = db!!.rawQuery(sql, selectionArgs)
        if (ex.moveToFirst()) {
            val idcours = ex.getColumnIndex("idcours")
            val auditory = ex.getColumnIndex("auditory")
            val day = ex.getColumnIndex("day")
            val starttime = ex.getColumnIndex("starttime")
            val type = ex.getColumnIndex("type")
            do {
                returned.add(LessonsLocal(
                        day = ex.getString(day),
                        auditory = ex.getString(auditory),
                        id_courses = Integer.parseInt(ex.getString(idcours)),
                        start_time = ex.getString(starttime),
                        type = ex.getString(type),
                        cours = SugarRecord.find(CoursesLocal::class.java, "idcourses = ?", ex.getString(idcours)).get(0)
                ))
            } while (ex.moveToNext())
        } else {
            Log.d("not found rows")
        }
        return returned
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