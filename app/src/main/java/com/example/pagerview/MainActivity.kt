package com.example.pagerview

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var viewPager:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val title=listOf<String>("Home","Favourite","Google Map")
        val toolBar:androidx.appcompat.widget.Toolbar=findViewById(R.id.toolBar)
        val tabLayout:TabLayout=findViewById(R.id.tablayout)
        viewPager=findViewById(R.id.viewpager)
        setSupportActionBar(toolBar)
        viewPager.adapter=Pageradapter(supportFragmentManager,lifecycle)
        TabLayoutMediator(tabLayout,viewPager){
            tab,position->
            if (position==0) {
                tab.text = title.get(position)
            }
            else if (position==1)
            {
                tab.text=title.get(position)
            }
            else if (position==2)
            {
                tab.text=title.get(position)
            }
            else
            {
                tab.text=title.get(position)
            }
        }.attach()
    }
}