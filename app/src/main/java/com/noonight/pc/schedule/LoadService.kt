package com.noonight.pc.schedule

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.noonight.pc.schedule.api.*
import com.noonight.pc.schedule.localDB.*
import com.noonight.pc.schedule.loger.Log
import com.orm.SugarRecord
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.TimeUnit

class LoadService(private val api: RestApi = RestApi()) : Service() {

    override fun onCreate() {
        super.onCreate()
        //DBManager().createLocalTables()
        Log.l()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //DBManager().deleteAllLocal()
        Thread(Runnable {
            //loadData()
            //counter()
            loadLocalData()
            stopSelf()
        }).start()
        //Toast.makeText(applicationContext, "LOAD COMPLETED", 2).show()
        return super.onStartCommand(intent, flags, startId)
    }

    fun counter() {
        for (i in 0..10) {
            Log.d(i.toString())
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
        Log.l()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.l()
    }

    private fun loadData() {
        loadLessons()
        loadCourses()
        loadListeners()
        loadUsers()
        loadUsersType()
    }

    private fun loadLocalData() {
        if (localUsersType()){
            if (localUsers()) {
                //localUsers()
                if (localCourses()) {
                    //localCourses()
                    if (localLessons()) {
                        //localLessons()
                        if (localListeners()) {
                            //localListeners()
                            //Toast.makeText(applicationContext, "complete load", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        /*Log.d(localUsersType().toString())
        Log.d(localUsers().toString())
        Log.d(localCourses().toString())
        Log.d(localLessons().toString())
        Log.d(localListeners().toString())*/

        /*localUsersType()
        Log.d(DBManager().getUsers_TypeLocal().toString())
        localUsers()
        Log.d(DBManager().getUsersLocal().toString())
        localCourses()
        localLessons()
        localListeners()*/
    }

    private fun localLessons(): Boolean {
        var returned = false;
        val response = api.getLessons().execute()
        if (response.isSuccessful) {
            val dataResponse = response.body()
            val lessons = dataResponse
            for (i in lessons) {
                LessonsLocal(
                        i.id_lesson,
                        i.day,
                        i.start_time,
                        i.long_time,
                        i.id_courses,
                        SugarRecord.find(CoursesLocal::class.java, "idcourses = ?", i.id_courses.toString()).get(0)
                ).save()
            }
            returned = true
        }
        return returned
    }

    /*private fun localLessons(): Boolean {
        var returned = false;
        api.getLessons().enqueue(object : Callback<List<Lessons>> {
            override fun onFailure(call: Call<List<Lessons>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Lessons>>?, response: Response<List<Lessons>>?) {
                val returnedData = response!!.body()
                //SugarRecord.deleteAll(LessonsLocal::class.java)
                for (i in 0 until returnedData.size) {
                    LessonsLocal(
                            returnedData.get(i).id_lesson,
                            returnedData.get(i).day,
                            returnedData.get(i).start_time,
                            returnedData.get(i).long_time,
                            returnedData.get(i).id_courses,
                            SugarRecord.find(CoursesLocal::class.java, "idcourses = ?", returnedData.get(i).id_courses.toString()).get(0)
                            //CoursesLocal()
                    ).save()
                    //Log.d(DBManager().getLessonsLocal().toString())
                }
                returned = true

            }
        })
        return returned
    }*/

    private fun localUsers(): Boolean {
        var returned = false
        /*api.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onFailure(call: Call<List<Users>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Users>>?, response: Response<List<Users>>?) {
                val returnedData = response!!.body()
                //SugarRecord.deleteAll(UsersLocal::class.java)
                for (i in 0 until returnedData.size) {
                    *//*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*//*
                    //Log.d(SugarRecord.find(Users_TypeLocal::class.java, "idusertype = ?", returnedData.get(i).id_user_type.toString()).toString())
                    UsersLocal(
                            returnedData.get(i).id_user,
                            returnedData.get(i).name,
                            returnedData.get(i).id_user_type,
                            SugarRecord.find(Users_TypeLocal::class.java, "idusertype = ?", returnedData.get(i).id_user_type.toString()).get(0)
                    ).save()
                    //Log.d(DBManager().getUsersLocal().toString())
                }
                returned = true
            }
        })*/
        val response = api.getUsers().execute()
        if (response.isSuccessful) {
            val dataResponse = response.body()
            val lessons = dataResponse
            for (i in lessons) {
                UsersLocal(
                        i.id_user,
                        i.name,
                        i.id_user_type,
                        SugarRecord.find(Users_TypeLocal::class.java, "idusertype = ?", i.id_user_type.toString()).get(0)
                ).save()
            }
            returned = true
        }
        return returned
    }

    private fun localUsersType(): Boolean {
        var returned = false
        /*api.getUsers_Type().enqueue(object : Callback<List<Users_Type>> {
            override fun onFailure(call: Call<List<Users_Type>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Users_Type>>?, response: Response<List<Users_Type>>?) {
                val returnedData = response!!.body()
                //SugarRecord.deleteAll(Users_TypeLocal::class.java)
                for (i in 0 until returnedData.size) {
                    *//*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*//*
                    Users_TypeLocal(
                            returnedData.get(i).id_user_type,
                            returnedData.get(i).user_type
                    ).save()
                }
                returned = true
            }
        })*/
        val response = api.getUsers_Type().execute()
        if (response.isSuccessful) {
            val dataResponse = response.body()
            val lessons = dataResponse
            for (i in lessons) {
                Users_TypeLocal(
                        i.id_user_type,
                        i.user_type
                ).save()
            }
            returned = true
        }
        return returned
    }

    private fun localCourses(): Boolean {
        var returned = false
        /*api.getCourses().enqueue(object : Callback<List<Courses>> {
            override fun onFailure(call: Call<List<Courses>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Courses>>?, response: Response<List<Courses>>?) {
                val returnedData = response!!.body()
                //SugarRecord.deleteAll(CoursesLocal::class.java)
                for (i in 0 until returnedData.size) {
                    *//*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*//*
                    CoursesLocal(
                            returnedData.get(i).id_courses,
                            returnedData.get(i).title,
                            returnedData.get(i).description,
                            returnedData.get(i).start_date,
                            returnedData.get(i).end_date,
                            returnedData.get(i).id_user_lecturer,
                            SugarRecord.find(UsersLocal::class.java, "iduser = ?", returnedData.get(i).id_user_lecturer.toString()).get(0)
                    ).save()
                }
                returned = true
            }
        })*/
        val response = api.getCourses().execute()
        if (response.isSuccessful) {
            val dataResponse = response.body()
            val lessons = dataResponse
            for (i in lessons) {
                CoursesLocal(
                        i.id_courses,
                        i.title,
                        i.description,
                        i.start_date,
                        i.end_date,
                        i.id_user_lecturer,
                        SugarRecord.find(UsersLocal::class.java, "iduser = ?", i.id_user_lecturer.toString()).get(0)
                ).save()
            }
            returned = true
        }
        return returned
    }

    private fun localListeners(): Boolean {
        var returned = false
       /* api.getListeners().enqueue(object : Callback<List<Listeners>> {
            override fun onFailure(call: Call<List<Listeners>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Listeners>>?, response: Response<List<Listeners>>?) {
                val returnedData = response!!.body()
                //SugarRecord.deleteAll(ListenersLocal::class.java)
                for (i in 0 until returnedData.size) {
                    *//*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*//*
                    //Log.d(SugarRecord.find(UsersLocal::class.java, "iduser = ?", returnedData.get(i).id_user.toString()).toString())
                    ListenersLocal(
                            returnedData.get(i).id_listener,
                            returnedData.get(i).id_user,
                            SugarRecord.find(UsersLocal::class.java, "iduser = ?", returnedData.get(i).id_user.toString())[0],
                            returnedData.get(i).id_courses,
                            SugarRecord.find(CoursesLocal::class.java, "idcourses = ?", returnedData.get(i).id_courses.toString())[0]
                    ).save()
                }
                returned = true
            }
        })*/
        val response = api.getListeners().execute()
        if (response.isSuccessful) {
            val dataResponse = response.body()
            val lessons = dataResponse
            for (i in lessons) {
                ListenersLocal(
                        i.id_listener,
                        i.id_user,
                        SugarRecord.find(UsersLocal::class.java, "iduser = ?", i.id_user.toString())[0],
                        i.id_courses,
                        SugarRecord.find(CoursesLocal::class.java, "idcourses = ?", i.id_courses.toString())[0]
                ).save()
            }
            returned = true
        }
        return returned
    }

    private fun loadLessons() {
        Log.l()
        api.getLessons().enqueue(object : Callback<List<Lessons>> {
            override fun onFailure(call: Call<List<Lessons>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Lessons>>?, response: Response<List<Lessons>>?) {
                val returnedData = response!!.body()
                SugarRecord.deleteAll(Lessons::class.java)
                for (i in 0 until returnedData.size) {
                    /*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*/
                    Lessons(
                            returnedData.get(i).id_lesson,
                            returnedData.get(i).day,
                            returnedData.get(i).start_time,
                            returnedData.get(i).long_time,
                            returnedData.get(i).id_courses
                    ).save()
                }
                /*Log.d(SugarRecord.listAll(Lessons::class.java).toString())
                val list: List<Lessons> = SugarRecord.listAll(Lessons::class.java)
                Log.d(list.toString())*/
            }
        })
    }

    private fun loadUsers() {
        Log.l()
        api.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onFailure(call: Call<List<Users>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Users>>?, response: Response<List<Users>>?) {
                val returnedData = response!!.body()
                SugarRecord.deleteAll(Users::class.java)
                for (i in 0 until returnedData.size) {
                    /*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*/
                    Users(
                            returnedData.get(i).id_user,
                            returnedData.get(i).name,
                            returnedData.get(i).id_user_type
                    ).save()
                }
            }
        })
    }

    private fun loadUsersType() {
        Log.l()
        api.getUsers_Type().enqueue(object : Callback<List<Users_Type>> {
            override fun onFailure(call: Call<List<Users_Type>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Users_Type>>?, response: Response<List<Users_Type>>?) {
                val returnedData = response!!.body()
                SugarRecord.deleteAll(Users_Type::class.java)
                for (i in 0 until returnedData.size) {
                    /*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*/
                    Users_Type(
                            returnedData.get(i).id_user_type,
                            returnedData.get(i).user_type
                    ).save()
                }
            }
        })
    }

    private fun loadCourses() {
        Log.l()
        api.getCourses().enqueue(object : Callback<List<Courses>> {
            override fun onFailure(call: Call<List<Courses>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Courses>>?, response: Response<List<Courses>>?) {
                val returnedData = response!!.body()
                SugarRecord.deleteAll(Courses::class.java)
                for (i in 0 until returnedData.size) {
                    /*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*/
                    Courses(
                            returnedData.get(i).id_courses,
                            returnedData.get(i).title,
                            returnedData.get(i).description,
                            returnedData.get(i).start_date,
                            returnedData.get(i).end_date,
                            returnedData.get(i).id_user_lecturer
                    ).save()
                }
            }
        })
    }

    private fun loadListeners() {
        Log.l()
        api.getListeners().enqueue(object : Callback<List<Listeners>> {
            override fun onFailure(call: Call<List<Listeners>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Listeners>>?, response: Response<List<Listeners>>?) {
                val returnedData = response!!.body()
                SugarRecord.deleteAll(Listeners::class.java)
                for (i in 0 until returnedData.size) {
                    /*Log.d(
                            "${returnedData.get(i).id_lesson.toString()} " +
                            "${returnedData.get(i).day.toString()} " +
                            "${returnedData.get(i).start_time.toString()} " +
                            "${returnedData.get(i).long_time.toString()} " +
                            "${returnedData.get(i).id_courses.toString()} "
                    )*/
                    Listeners(
                            returnedData.get(i).id_listener,
                            returnedData.get(i).id_user,
                            returnedData.get(i).id_courses
                    ).save()
                }
            }
        })
    }
}