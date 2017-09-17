package com.noonight.pc.schedule.schedules.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.schedules.LessonsItem

/**
 * Created by PC on 9/17/2017.
 */
class LessonDelegateAdapter : ScheduleDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.schedule_item)) {
        fun bind(item : LessonsItem) = with(itemView) {
            id_lesson.text
        }
    }
}