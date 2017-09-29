package com.noonight.pc.schedule.courses


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.noonight.pc.schedule.R
import com.noonight.pc.schedule.extensions.inflate


/**
 * A simple [Fragment] subclass.
 */
class CoursesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_courses)
        //return inflater!!.inflate(R.layout.fragment_courses, container, false)
    }

}// Required empty public constructor
