package com.noonight.pc.schedule.courses.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.noonight.pc.schedule.MainActivity
import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.extensions.loger.Log
import com.noonight.pc.schedule.extensions.toast
import com.noonight.pc.schedule.localDB.CoursesLocal
import kotlinx.android.synthetic.main.courses_fragment.*
import kotlinx.android.synthetic.main.courses_item.view.*


class CourseDelegateAdapter : CourseDelegateAdapterInterface {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: CoursesLocal) {
        holder as TurnsViewHolder
        holder.bind(item as CoursesLocal)
        //holder.itemView.setOnClickListener { Log.d("${item.id}") }
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.courses_item)
    ) {
        fun bind(item: CoursesLocal) = with(itemView) {
            //itemView.setOnClickListener { Log.d("$adapterPosition") }
            tvCourseItemTitle.text = item.title
            tvCourseItemDescription.text = item.description
            tvCourseItemTeacher.text = item.user_lecturer?.name
            tvCourseItemStartDate.text = item.start_date
            tvCourseItemEndDate.text = item.end_date
        }
    }
}