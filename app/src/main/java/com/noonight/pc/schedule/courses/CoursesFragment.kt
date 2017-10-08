package com.noonight.pc.schedule.courses

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.noonight.pc.schedule.*

import com.noonight.pc.schedule.courses.adapter.CourseAdapter
import com.noonight.pc.schedule.courses.single_course.CourseSingleFragment
import com.noonight.pc.schedule.extensions.consume
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.extensions.loger.Log
import com.noonight.pc.schedule.extensions.toast
import com.noonight.pc.schedule.localDB.DBManager
import kotlinx.android.synthetic.main.courses_fragment.*

class CoursesFragment : Fragment(), FragmentTitleInterface, FragmentTagNameInterface, InstanceInterface {
    var rootActivity: Activity? = null

    companion object {
        fun newInstance(args: Bundle): CoursesFragment {
            val thisFragment = CoursesFragment()
            thisFragment.arguments = args
            return thisFragment
        }
    }

    override fun newFragment(activity: Activity): Fragment {
        rootActivity = activity
        return this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.courses_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()

        initAdapter()

        (courses_list.adapter as CourseAdapter).putContext(context = context, activity = rootActivity!!)

        addData()
    }

    fun initList() {
        courses_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }

    }

    fun openItemFragment() {
        activity.supportFragmentManager.beginTransaction().add(CourseSingleFragment(), CourseSingleFragment::getTagName.toString()).commit()
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
