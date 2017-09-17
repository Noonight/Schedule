package com.noonight.pc.schedule.schedules

import android.os.Parcel
import android.os.Parcelable
import com.noonight.pc.schedule.extensions.createParcel

/**
 * Created by PC on 9/17/2017.
 */
data class Lessons(
        val lessonsItems: List<LessonsItem>
) : Parcelable {
    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { Lessons(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            mutableListOf<LessonsItem>().apply {
                parcelIn.readTypedList(this, LessonsItem.CREATOR)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(lessonsItems)
    }

    override fun describeContents(): Int {
        return 0
    }
}

data class LessonsItem(
        val id_lesson: Int,
        val day: String,
        val start_time: String,
        val long_time: Int,
        val id_courses: Int
) : Parcelable {
    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { LessonsItem(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readInt(),
            parcelIn.readString(),
            parcelIn.readString(),
            parcelIn.readInt(),
            parcelIn.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id_lesson)
        dest.writeString(day)
        dest.writeString(start_time)
        dest.writeInt(long_time)
        dest.writeInt(id_courses)
    }

    override fun describeContents() = 0
}