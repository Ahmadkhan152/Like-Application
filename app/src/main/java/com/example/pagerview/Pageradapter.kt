package com.example.pagerview


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class Pageradapter(val fragment: FragmentManager,val lifeCycle:Lifecycle) : FragmentStateAdapter(fragment ,lifeCycle) {
    private val title=listOf<String>("Home","Favourite","Google Map")

    override fun getItemCount(): Int {
        return title.size
    }

    override fun createFragment(position: Int): Fragment {
        if (position==0)
            return Tab1_home()
        else if (position==1)
            return Tab2_favourite()
        else if(position==2)
            return Tab3_googlemap()
        else
            return Tab1_home()
    }

}