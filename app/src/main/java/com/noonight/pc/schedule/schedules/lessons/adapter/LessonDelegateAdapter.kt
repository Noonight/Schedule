package com.noonight.pc.schedule.schedules.lessons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.schedules.LessonsItem
import kotlinx.android.synthetic.main.schedule_item.view.*

/**
 * Created by PC on 9/17/2017.
 */
class LessonDelegateAdapter : ScheduleDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item : LessonsItem) {
        holder as TurnsViewHolder
        holder.bind(item as LessonsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.schedule_item)) {
        fun bind(item : LessonsItem) = with(itemView) {
            id_lesson.text = item.id_lesson.toString()
            day.text = item.day
            start_time.text = item.start_time
            long_time.text = item.long_time.toString()
            id_courses.text = item.id_courses.toString()
        }
    }
}