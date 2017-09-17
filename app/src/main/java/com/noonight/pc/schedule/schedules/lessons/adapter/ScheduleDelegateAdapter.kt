package com.noonight.pc.schedule.schedules.lessons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noonight.pc.schedule.schedules.LessonsItem

/**
 * Created by PC on 9/17/2017.
 */
interface ScheduleDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item : LessonsItem)

}