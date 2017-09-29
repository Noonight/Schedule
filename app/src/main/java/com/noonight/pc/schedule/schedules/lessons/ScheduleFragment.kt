package com.noonight.pc.schedule.schedules.lessons

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noonight.pc.schedule.LoadService
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.api.Lessons
import com.noonight.pc.schedule.api.RestApi
import com.noonight.pc.schedule.schedules.lessons.adapter.LessonAdapter
import kotlinx.android.synthetic.main.schedule_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleFragment : Fragment() {

    private var lessons: Lessons? = null
    private val lessonsManager by lazy {
        ScheduleManager()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.schedule_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //btnStartService.setOnClickListener { activity.startService(Intent(context, LoadService::class.java)) }
        //btnStopService.setOnClickListener { activity.stopService(Intent(context, LoadService::class.java)) }
        lessons_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }

        initAdapter()

        /*val intent = Intent(context, LoadService::class.java)
        activity.startService(intent)*/

        addData()
        //(lessons_list.adapter as LessonAdapter).addLessons(lessonsManager.getLessons())
    }
    //TODO its bad need paralel lib -> rx2 and use up code!!!
    private fun addData() {
        val api = RestApi()
        api.getLessons().enqueue(object : Callback<List<Lessons>> {
            override fun onFailure(call: Call<List<Lessons>>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<List<Lessons>>?, response: Response<List<Lessons>>?) {
                (lessons_list.adapter as LessonAdapter).addLessons(response!!.body())
            }
        })
        /*val intent = Intent(context, LoadService::class.java)
        activity.startService(intent)*/
    }

    private fun initAdapter() {
        if (lessons_list.adapter == null)
            lessons_list.adapter = LessonAdapter()
    }



}// Required empty public constructor
