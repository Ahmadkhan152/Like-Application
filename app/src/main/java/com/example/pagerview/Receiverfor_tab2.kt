package com.example.pagerview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast

class Receiverfor_tab2(val tab2Favourite: Tab2_favourite): BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val name=p1?.getStringExtra(NAME).toString()
        val phoneNumber=p1?.getStringExtra(PHONE_NUMBER).toString()
        val latitude=p1?.getDoubleExtra(LATITUDE,-1.0).toString().toDouble()
        val longitude=p1?.getDoubleExtra(LONGITUDE,-1.0).toString().toDouble()
        val obj=Data(name,phoneNumber,latitude,longitude)
        tab2Favourite.arrayOfObj.add(obj)
        if (tab2Favourite.arrayOfObj.size!=0)
            tab2Favourite.tvNorecord.visibility= View.INVISIBLE
        tab2Favourite.adapter=Myrecycleradapter(tab2Favourite.context,tab2Favourite.arrayOfObj,tab2Favourite,tab2Favourite.tvNorecord)
        tab2Favourite.recyclerView.adapter=tab2Favourite.adapter
    }
}