package com.noonight.pc.schedule.courses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noonight.pc.schedule.FragmentTagNameInterface
import com.noonight.pc.schedule.FragmentTitleInterface

import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.courses.adapter.CourseAdapter
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.localDB.DBManager
import kotlinx.android.synthetic.main.courses_fragment.*

class CoursesFragment : Fragment(), FragmentTitleInterface, FragmentTagNameInterface {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.courses_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        courses_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }

        initAdapter()

        addData()
    }

    private fun initAdapter() {
        if (courses_list.adapter == null)
            courses_list.adapter = CourseAdapter()
    }

    private fun addData() {
        (courses_list.adapter as CourseAdapter).addCourses(DBManager().getCoursesLocal())
    }

    override fun getTitle(): String = "Курсы"
    override fun getTagName(): String = "Курсы"
}
