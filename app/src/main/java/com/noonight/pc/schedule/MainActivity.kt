package com.noonight.pc.schedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.Toolbar
//import android.widget.Toolbar
import com.noonight.pc.schedule.loger.Log
import com.noonight.pc.schedule.schedules.ScheduleFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val toolbar: Toolbar by lazy {
        toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        Log.d("savedInstanceState -> $savedInstanceState")
        if (savedInstanceState == null) {
            changeFragment(ScheduleFragment())
        }
    }

    fun changeFragment(fragment: Fragment, cleanStack: Boolean = false) {
        Log.d("run method changeFragment")
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        fragmentTransaction.setCustomAnimations(
                R.anim.abc_fade_in,
                R.anim.abc_fade_out,
                R.anim.abc_popup_enter,
                R.anim.abc_popup_exit
        )
        fragmentTransaction.replace(R.id.activity_base_content, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun clearBackStack() {
        Log.d("run method clearBackStack()")
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            Log.d("getBackStackEntryAt(0) -> from supportFragmentManager -> $first ;\n" +
                    "id -> ${first.id}")
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            Log.d("supportFragmentManager.popBackStack(${first.id}, " +
                    "FragmentManager.POP_BACK_STACK_INCLUSIVE -> ${FragmentManager.POP_BACK_STACK_INCLUSIVE})")

        }
    }

    override fun onBackPressed() {
        Log.d("run method onBackPressed")
        val fragmentManager = supportFragmentManager
        Log.d("before if -> backStackEntryCount -> ${fragmentManager.backStackEntryCount}")
        if (fragmentManager.backStackEntryCount > 1) {
            Log.d("using popbackStack")
            fragmentManager.popBackStack()
        } else {
            Log.d("out app")
            finish()
        }
    }
}
