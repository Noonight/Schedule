package com.noonight.pc.schedule.schedules


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noonight.pc.schedule.extensions.inflate
import com.noonight.pc.schedule.R


/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return container?.inflate(R.layout.schedule_fragment)
        //return inflater!!.inflate(R.layout.fragment_schedule, container, false)
    }

}// Required empty public constructor
