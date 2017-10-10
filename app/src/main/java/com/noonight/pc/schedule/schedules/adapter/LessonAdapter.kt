package com.noonight.pc.schedule.schedules.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noonight.pc.schedule.api.Lessons
import com.noonight.pc.schedule.localDB.LessonsLocal
import com.noonight.pc.schedule.extensions.loger.Log

class LessonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<LessonInterface>
    private var delegateAdapters = mutableListOf<ScheduleDelegateAdapter>()

    init {
        delegateAdapters.add(LessonDelegateAdapter())
        items = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addLessonsLocal(lessons: List<LessonsLocal>) {
        Log.d(lessons.toString())
        //val initPosition = items.size - 1
        //items.removeAt(initPosition)
        //notifyItemRemoved(initPosition)

        items.addAll(lessons)
        notifyItemRangeChanged(0, items.size)
    }

    fun update() {
        notifyItemRangeChanged(0, items.size)
    }

    fun addLessons(lessons: List<Lessons>) {
        Log.d(lessons.toString())
        //val initPosition = items.size - 1
        //items.removeAt(initPosition)
        //notifyItemRemoved(initPosition)

        items.addAll(lessons)
        notifyItemRangeChanged(0, items.size)
    }

    fun clearAndAddLessons(lessons: List<Lessons>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(lessons)
        //items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)

    }

    fun getLessons(): List<Lessons> {
        return items
                //.filter { it.getViewType() == AdapterConstants.ITEM }
                .map { it as Lessons }
                //.filter { it.getViewType() == AdapterConstants.NEWS }
                //.map { it }

    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}