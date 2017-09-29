package com.noonight.pc.schedule

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import com.noonight.pc.schedule.api.Lessons
//import android.widget.Toolbar
import com.noonight.pc.schedule.loger.Log
import com.noonight.pc.schedule.schedules.lessons.ScheduleFragment
import com.orm.SugarRecord
import com.orm.SugarRecord.listAll
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, LoadService::class.java))
        /*val intent = Intent(this, LoadService::class.java)
        startService(intent)*/
        //Log.d(SugarRecord.listAll(Lessons::class.java).toString())
        changeFragment(ScheduleFragment())
    }

    fun changeFragment(fragment: Fragment, cleanStack: Boolean = false) {
        Log.l()
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
        Log.l()
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)

            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        Log.l()
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
