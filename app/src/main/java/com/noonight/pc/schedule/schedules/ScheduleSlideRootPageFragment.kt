package com.noonight.pc.schedule.schedules

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


class ScheduleSlideRootPageFragment : Fragment(), FragmentTagNameInterface, FragmentTitleInterface {
    override fun getTitle(): String {
        return "РутРасписание"
    }

    override fun getTagName(): String {
        return "РутРасписание"
    }

    private var rootView: View? = null
    private var viewPager: ViewPager? = null

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
        viewPager?.adapter = ScreenSlidePageAdapter(childFragmentManager)
        return rootView
    }

    private class ScreenSlidePageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val args = Bundle()
            args.putInt(ScheduleFragment.POSITION_KEY, position)
            return ScheduleFragment.newInstance(args)
        }

        override fun getCount(): Int {
            return 10
        }

        fun getItemsOnCourse() {

        }
    }
}