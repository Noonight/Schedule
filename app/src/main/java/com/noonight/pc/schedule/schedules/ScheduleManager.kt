package com.noonight.pc.schedule.schedules


import android.os.AsyncTask
import com.noonight.pc.schedule.api.Lessons
import com.noonight.pc.schedule.api.RestApi
import com.noonight.pc.schedule.loger.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleManager(private val api: RestApi = RestApi()) {
    fun getLessons(): List<Lessons> {
        //api.getLessons().enqueue()
        val callResponse = api.getLessons()
        val callBack = CallBacks()
        callResponse.enqueue(callBack)
        //callResponse.enqueue()
        //try {
            //callResponse.execute()
        /*} catch (error: Exception) {
            Log.d(error.toString())
        }*/

        /*val response = callResponse.execute()

        if (response.isSuccessful) {
            val dataResponse = response.body()
            val lessons = dataResponse?.map {
                val item = it
                LessonsItem(
                        item.id_lesson,
                        item.day,
                        item.start_time,
                        item.long_time,
                        item.id_courses
                )
            }
            val briopLessons = Lessons(lessons!!)
            Log.d(briopLessons.toString())
            return briopLessons
        }
        return null*/
        //println(callBack.returned.toString())
        return callBack.returned
        /*val doit = DoIt()
        doit.execute()
        Log.d(doit.getLessons().toString())*/
        //return doit.getLessons()
    }

    class CallBacks : Callback<List<Lessons>> {
        val returned = mutableListOf<Lessons>()
        override fun onFailure(call: Call<List<Lessons>>?, t: Throwable?) {
        }

        override fun onResponse(call: Call<List<Lessons>>?, response: Response<List<Lessons>>?) {
            val lessons: List<Lessons> = response?.body()!!
            returned.addAll(lessons)
            Log.d(lessons.toString())
        }
    }

    private class DoIt : AsyncTask<Unit, Unit, List<Lessons>>() {
        private var lessonse = mutableListOf<Lessons>()

        override fun doInBackground(vararg p0: Unit?): List<Lessons> {
            val api = RestApi()
            val response = api.getLessons().execute()
            if (response.isSuccessful) {
                val dataResponse = response.body()
                val lessons = dataResponse.map {
                    Lessons(
                            it.id_lesson,
                            it.day,
                            it.start_time,
                            it.long_time,
                            it.id_courses
                    )
                }
                lessonse = lessons as MutableList<Lessons>
                Log.d(lessonse.toString())
                return lessons
            }
            return lessonse
        }

        override fun onPostExecute(result: List<Lessons>?) {
            super.onPostExecute(result)
            lessonse = result as MutableList<Lessons>
        }

        override fun onProgressUpdate(vararg values: Unit?) {
            super.onProgressUpdate(*values)
            lessonse = doInBackground() as MutableList<Lessons>
        }

        fun getLessons(): MutableList<Lessons> {
            //lessonse = doInBackground() as MutableList<Lessons>
            onPostExecute(doInBackground())
            return lessonse
        }
    }

    /*var lessons: List<Lessons>
        get() {
            api.getLessons().enqueue(object : Callback<List<Lessons>> {
                override fun onFailure(call: Call<List<Lessons>>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<List<Lessons>>?, response: Response<List<Lessons>>?) {
                    lessons = response!!.body()
                    Log.d(lessons.toString())
                }

            })
            return lessons
        }
        set(value) {
            lessons = value
        }*/
}
