package com.example.pagerview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View

class Receiverfor_tab1(val tab1Home:Tab1_home): BroadcastReceiver() {

            override fun onReceive(p0: Context?, p1: Intent?) {
                val name=p1?.getStringExtra(NAME).toString()
                val phoneNumber=p1?.getStringExtra(PHONE_NUMBER).toString()
                val latitude=p1?.getDoubleExtra(LATITUDE,-1.0).toString().toDouble()
                val longitude=p1?.getDoubleExtra(LONGITUDE,-1.0).toString().toDouble()
                val obj=Data(name,phoneNumber,latitude,longitude)
                tab1Home.arrayOfObj.add(obj)
                if (tab1Home.arrayOfObj.size!=0)
                    tab1Home.tvNorecord.visibility= View.INVISIBLE
                tab1Home.adapter=Myrecycleradapter(tab1Home.context,tab1Home.arrayOfObj,tab1Home,tab1Home.tvNorecord)
                tab1Home.recyclerView.adapter=tab1Home.adapter
            }
}