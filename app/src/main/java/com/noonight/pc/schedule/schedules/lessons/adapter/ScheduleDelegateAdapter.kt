package com.noonight.pc.schedule.schedules.lessons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

interface ScheduleDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item : LessonInterface)

}