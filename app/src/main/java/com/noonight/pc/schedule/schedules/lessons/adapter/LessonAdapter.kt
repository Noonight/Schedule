package com.noonight.pc.schedule.schedules.lessons.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noonight.pc.schedule.schedules.LessonsItem


class LessonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<LessonsItem>
    private var delegateAdapters = SparseArrayCompat<ScheduleDelegateAdapter>()

    init {
        delegateAdapters.put(1, LessonDelegateAdapter())
        items = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(1).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addLessons(lessons: List<LessonsItem>) {
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(lessons)
        //items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddLessons(lessons: List<LessonsItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(lessons)
        //items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)

    }

    fun getLessons(): List<LessonsItem> {
        return items
                //.filter { it.getViewType() == AdapterConstants.NEWS }
                .map { it as LessonsItem }

    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}