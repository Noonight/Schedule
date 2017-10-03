package com.noonight.pc.schedule.courses.single_course

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noonight.pc.schedule.FragmentTagNameInterface
import com.noonight.pc.schedule.FragmentTitleInterface
import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.extensions.inflate
import kotlinx.android.synthetic.main.course_single_fragment.*


class CourseSingleFragment : Fragment(), FragmentTitleInterface, FragmentTagNameInterface {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.course_single_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind()
    }

    fun bind() {
        tvCourseSingleTitle.text = arguments.getString("title", "")
        tvCourseSingleLecturer.text = arguments.getString("lecturer", "")
        tvCourseSingleDate.text = arguments.getString("date", "")
        tvCourseSingleDescription.text = arguments.getString("description", "")
    }

    override fun getTitle(): String = "Авторизация"
    override fun getTagName(): String = "Авторизация"
}