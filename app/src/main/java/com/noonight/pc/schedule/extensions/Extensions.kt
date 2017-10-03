package com.noonight.pc.schedule.extensions

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.noonight.pc.schedule.MainActivity

/**
 * Created by PC on 9/17/2017.
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun toast(str: String) {
    //Toast.makeText(MainActivity.context, str, Toast.LENGTH_SHORT).show()

}

inline fun consume(function: () -> Unit): Boolean {
    function()
    return true
}

inline fun <reified T : Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?) : Parcelable.Creator<T> =
        object  : Parcelable.Creator<T> {
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)

            override fun createFromParcel(source: Parcel?): T? = createFromParcel(source)

        }