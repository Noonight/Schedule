package com.noonight.pc.schedule.schedules

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noonight.pc.schedule.FragmentTagNameInterface
import com.noonight.pc.schedule.FragmentTitleInterface
import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.extensions.loger.Log
import com.noonight.pc.schedule.localDB.DBManager
import java.sql.Date


class ScheduleSlideRootPageFragment : Fragment(), FragmentTagNameInterface, FragmentTitleInterface {
    private var rootView: View? = null
    private var viewPager: ViewPager? = null
    var idUser: String? = null

    companion object {
        fun newInstance(args: Bundle): ScheduleSlideRootPageFragment {
            val thisFragment = ScheduleSlideRootPageFragment()
            thisFragment.arguments = args
            return thisFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = container?.inflate(R.layout.schedule_root_page_fragment)
        viewPager = rootView?.findViewById(R.id.vpScheduleRoot) as ViewPager

        idUser = arguments.getString("id_user")

        viewPager?.adapter = ScreenSlidePageAdapter.newInstance(childFragmentManager, idUser!!, context)
        return rootView
    }

    private class ScreenSlidePageAdapter(fm: FragmentManager, context: Context) : FragmentStatePagerAdapter(fm) {

        var idUser: String? = null
        val db = DBManager.newInstance(context)
        companion object {
            fun newInstance(fm: FragmentManager, idUser: String, context: Context): ScreenSlidePageAdapter {
                val thisAdapter = ScreenSlidePageAdapter(fm, context)
                thisAdapter.idUser = idUser
                return thisAdapter
            }
        }

        override fun getItem(position: Int): Fragment {
            val args = Bundle()
            args.putInt(ScheduleFragment.POSITION_KEY, position)
            var date = getItemsDate(position)
            args.putString("day", date.toString())
            args.putString("id_user", idUser)
            return ScheduleFragment.newInstance(args)
        }

        override fun getCount(): Int {
            return db.getCountDayLessonsForUser(idUser.toString())
        }

        override fun getPageTitle(position: Int): CharSequence {
            return getItemsDate(position).toString()
        }

        fun getItemsDate(position: Int): Date {
            var array = db.getArrayDayLessonsForUser(idUser.toString())
            return array.get(position)
            //Log.d(array.toString())
        }

        fun getAllLessons() {

        }
    }

    override fun getTitle(): String {
        return "РутРасписание"
    }

    override fun getTagName(): String {
        return "РутРасписание"
    }
}