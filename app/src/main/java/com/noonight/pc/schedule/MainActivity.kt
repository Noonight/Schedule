package com.noonight.pc.schedule

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.noonight.pc.schedule.localDB.DBManager
import com.noonight.pc.schedule.localDB.Users_TypeLocal
//import android.widget.Toolbar
import com.noonight.pc.schedule.loger.Log
import com.noonight.pc.schedule.schedules.ScheduleFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addToolbar()

        //startService(Intent(this, LoadService::class.java))

       // DBManager().query()

        changeFragment(ScheduleFragment())
    }

    private var drawerToggle: ActionBarDrawerToggle? = null

    fun addToolbar() {
        setSupportActionBar(toolbar)

        setupDrawerContent(nvView)


        //nvView.addHeaderView(nvView.inflateHeaderView(R.layout.nav_header))
        val headerLayout: View = nvView.inflateHeaderView(R.layout.nav_header)
        val headerText: TextView = headerLayout.findViewById(R.id.header) as TextView
        headerText.text = "My Second Simple Header"
        //header.text = "My simple header"
        //nvView.header.text="My First Simple Header"

        drawerToggle = setupDrawerToggle()
        drawer_layout.addDrawerListener(drawerToggle!!)
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
        navigationView.setNavigationItemSelectedListener(object: NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                selectDrawerItem(item)
                return true
            }
        })
        //navigationView.setNavigationItemSelectedListener({item -> selectDrawerItem(item); return@setNavigationItemSelectedListener true })
    }
    var prevTitle: CharSequence? = null
    fun selectDrawerItem(menuItem: MenuItem) {
        var fragment: Fragment? = null
        when (menuItem.itemId) {
            R.id.nav_schedule -> fragment = ScheduleFragment()
            R.id.nav_second_fragment -> Toast.makeText(this, "second clicked", 2).show()
            R.id.nav_update_db -> {
                //DBManager().deleteAllLocal()
                startService(Intent(this, LoadService::class.java))
                //TimeUnit.MINUTES.sleep(1)
                //fragment = ScheduleFragment()
                //ScheduleFragment().addData()
                toast("Don't working")
            }
            R.id.nav_sub_first -> Toast.makeText(this, "sub item first clicked", 2).show()
            R.id.nav_sub_second -> toast("sub item second clicked")
            else -> fragment = ScheduleFragment()
        }
        if (fragment != null)
            changeFragment(fragment!!)
        menuItem.setChecked(true)
        prevTitle = title
        setTitle(menuItem.title)
        drawer_layout.closeDrawers()
    }

    private fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean/* = when (item.itemId) {
        R.id.home -> consume { drawer_layout.openDrawer(GravityCompat.START) }
        else -> super.onOptionsItemSelected(item)*/
    {
        if (drawerToggle!!.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    inline fun consume(function: () -> Unit): Boolean {
        function()
        return true
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
            setTitle(prevTitle)
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
