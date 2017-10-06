package com.noonight.pc.schedule

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.noonight.pc.schedule.courses.CoursesFragment
import com.noonight.pc.schedule.localDB.DBManager
//import android.widget.Toolbar
import com.noonight.pc.schedule.extensions.loger.Log
import com.noonight.pc.schedule.schedules.ScheduleFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

    var prevTitle: CharSequence? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var currentLogin: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentLogin = intent.getStringExtra("login")
        toast("login is = $currentLogin")
        addToolbar()
        val frag = ScheduleFragment()
        val bundle = Bundle()
        bundle.putString("user", currentLogin)
        frag.arguments = bundle
        changeFragment(frag)
    }

    fun addToolbar() {
        setSupportActionBar(toolbar)

        setupDrawerContent(nvView)

        val headerLayout: View = nvView.inflateHeaderView(R.layout.nav_header)
        val headerText: TextView = headerLayout.findViewById(R.id.header) as TextView
        headerText.text = "Your login name here\nMay be photo too"

        drawerToggle = setupDrawerToggle()
        drawer_layout.addDrawerListener(drawerToggle!!)

        nvView.menu.findItem(R.id.nav_schedule).setChecked(true)
    }

    fun setupDrawerToggle(): ActionBarDrawerToggle {
        return ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                selectDrawerItem(item)
                return true
            }
        })
        //navigationView.setNavigationItemSelectedListener({item -> selectDrawerItem(item); return@setNavigationItemSelectedListener true })
    }

    fun selectDrawerItem(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_schedule -> {
                changeCurentViewFragment(ScheduleFragment())
                menuItem.setChecked(true)
            }
            R.id.nav_clear_db -> {
                DBManager().deleteAllLocal()
                toast("Delete complete but... \ndon't working")
            }
            R.id.nav_update_db -> {
                startService(Intent(this, LoadService::class.java))
            }
            R.id.nav_courses -> {
                changeCurentViewFragment(CoursesFragment().newFragment(this))
                menuItem.setChecked(true)
            }
            R.id.nav_log_out -> {
                toast("Действия ещё нет. Пусто!")
            }
            else -> toast("don't clicked")
        }
    }

    private fun changeCurentViewFragment(fragment: Fragment) {
        val curFragment = supportFragmentManager.findFragmentByTag(ScheduleFragment().getTagName())
        if (curFragment.isVisible && curFragment == fragment.targetFragment) {
        } else {
            if (fragment != null) {
                changeFragment(fragment)
                prevTitle = title
                setTitle((fragment as FragmentTitleInterface).getTitle())
                drawer_layout.closeDrawers()
            }
        }
    }

    fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean/* = when (item.itemId) {
        R.id.home -> consume { drawer_layout.openDrawer(GravityCompat.START) }
        else -> super.onOptionsItemSelected(item)*/ {
        if (drawerToggle!!.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }


    var prevFragment: Fragment? = null

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
        prevFragment = fragment
        fragmentTransaction.replace(R.id.activity_base_content, fragment, (fragment as FragmentTagNameInterface).getTagName())
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
            setTitle(prevTitle)
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
