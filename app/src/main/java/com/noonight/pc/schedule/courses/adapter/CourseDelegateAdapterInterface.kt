package com.noonight.pc.schedule.courses.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noonight.pc.schedule.localDB.CoursesLocal


interface CourseDelegateAdapterInterface {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: CoursesLocal)
}