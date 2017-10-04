package com.noonight.pc.schedule

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment


interface InstanceInterface {
    fun newFragment(activity: Activity): Fragment
}