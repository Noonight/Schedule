package com.noonight.pc.schedule.courses.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noonight.pc.schedule.localDB.CoursesLocal
import com.noonight.pc.schedule.extensions.loger.Log


class CourseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<CoursesLocal>
    private var delegateAdapters = mutableListOf<CourseDelegateAdapterInterface>()

    init {
        delegateAdapters.add(CourseDelegateAdapter())
        items = ArrayList()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
            = delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    fun addCourses(courses: List<CoursesLocal>) {
        Log.d(courses.toString())
        items.addAll(courses)
        notifyItemRangeChanged(0, items.size)
    }
}