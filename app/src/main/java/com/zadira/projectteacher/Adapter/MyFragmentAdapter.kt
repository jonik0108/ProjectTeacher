package com.zadira.test3.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zadira.projectteacher.Fragment.Two
import com.zadira.test3.Fragment.*

class MyFragmentAdapter(var list: ArrayList<Fragment>, fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getCount(): Int = 6

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> One()
            1 -> Two()
            2 -> Three()
            3 -> Four()
            4 -> Five()
            5 -> Six()

            else -> One()
        }
    }
}