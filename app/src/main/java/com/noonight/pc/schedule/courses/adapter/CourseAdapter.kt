package com.noonight.pc.schedule.courses.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Toast
import com.noonight.pc.schedule.MainActivity
import com.noonight.pc.schedule.courses.single_course.CourseSingleFragment
import com.noonight.pc.schedule.localDB.CoursesLocal
import com.noonight.pc.schedule.extensions.loger.Log
import com.noonight.pc.schedule.extensions.toast


class CourseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<CoursesLocal>
    private var delegateAdapters = mutableListOf<CourseDelegateAdapterInterface>()

    init {
        delegateAdapters.add(CourseDelegateAdapter())
        items = ArrayList()
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { click(position) }
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    fun click(position: Int) {
        val args = Bundle()
        args.putString("title", items[position].title)
        args.putString("lecturer", items[position].user_lecturer?.name)
        args.putString("date", "${items[position].start_date} - ${items[position].end_date}")
        args.putString("description", items[position].description)
        val childFragment = CourseSingleFragment()
        childFragment.arguments = args
        (activity as MainActivity).changeFragment(childFragment)
    }

    var context: Context? = null
    var activity: Activity? = null

    fun putContext(context: Context, activity: Activity) {
        this.context = context
        this.activity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    fun addCourses(courses: List<CoursesLocal>) {
        Log.d(courses.toString())
        items.addAll(courses)
        notifyItemRangeChanged(0, items.size)
    }


}