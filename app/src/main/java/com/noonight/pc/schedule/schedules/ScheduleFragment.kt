package com.noonight.pc.schedule.schedules

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noonight.pc.schedule.FragmentTagNameInterface
import com.noonight.pc.schedule.FragmentTitleInterface
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.api.Lessons
import com.noonight.pc.schedule.localDB.DBManager
import com.noonight.pc.schedule.schedules.adapter.LessonAdapter
import kotlinx.android.synthetic.main.schedule_fragment.*

class ScheduleFragment : Fragment(), FragmentTitleInterface, FragmentTagNameInterface {
    private var lessons: Lessons? = null
    private val lessonsManager by lazy {
        ScheduleManager()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.schedule_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lessons_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }

        initAdapter()

        addData()
    }

    fun reloadData() {
        initAdapter()
        addData()
    }
    //TODO its bad need paralel lib -> rx2 and use up code!!! Через Сервис и Thread
    fun addData() {
        (lessons_list.adapter as LessonAdapter).addLessonsLocal(DBManager().getLessonsLocal())
        /*val api = RestApi()
        api.getLessons().enqueue(object : Callback<List<Lessons>> {
            override fun onFailure(call: Call<List<Lessons>>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<List<Lessons>>?, response: Response<List<Lessons>>?) {
                (lessons_list.adapter as LessonAdapter).addLessons(response!!.body())
            }
        })*/
    }

    private fun initAdapter() {
        if (lessons_list.adapter == null)
            lessons_list.adapter = LessonAdapter()
    }

    override fun getTitle(): String = "Расписание"
    override fun getTagName(): String = "Расписание"
}
