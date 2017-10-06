package com.noonight.pc.schedule.schedules.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.localDB.LessonsLocal
import kotlinx.android.synthetic.main.schedule_item.view.*

class LessonDelegateAdapter : ScheduleDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item : LessonInterface) {
        holder as TurnsViewHolder
        //holder.bind(item as Lessons)
        holder.bind(item as LessonsLocal)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.schedule_item)
    ) {
        //fun bind(item : Lessons) = with(itemView) {
        fun bind(item : LessonsLocal) = with(itemView) {
            tvScheduleItemLecturer.text = item.cours?.user_lecturer?.name

            day.text = item.day
            tvScheduleItemStartTime.text = item.start_time.substring(0, item.start_time.length - 3)
            tvScheduleItemTitle.text = item.cours?.title
        }
    }
}