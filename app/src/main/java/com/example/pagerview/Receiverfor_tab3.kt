package com.example.pagerview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Receiverfor_tab3(val tab3googlemap: Tab3_googlemap): BroadcastReceiver() {


    companion object{
        var arrayOfObj= mutableListOf<Data>()
    }
    override fun onReceive(p0: Context?, p1: Intent?) {
        val name=p1?.getStringExtra(NAME).toString()
        val phoneNumber=p1?.getStringExtra(PHONE_NUMBER).toString()
        tab3googlemap.latitude=p1?.getDoubleExtra(LATITUDE,-1.0).toString().toDouble()
        tab3googlemap.longitude=p1?.getDoubleExtra(LONGITUDE,-1.0).toString().toDouble()
        tab3googlemap.mapFragment.getMapAsync {
            tab3googlemap.googleMap = it
            if (name=="-1" && phoneNumber=="-1")
            {
                tab3googlemap.googleMap.clear()
                var index:Int=0
                while (index<arrayOfObj.size)
                {
                    tab3googlemap.location = LatLng(arrayOfObj[index].latitude, arrayOfObj[index].longitude)
                    tab3googlemap.googleMap.addMarker(MarkerOptions().position(tab3googlemap.location).title("Location Marked"))
                    index++
                }
            }
            else
            {
                tab3googlemap.location = LatLng(tab3googlemap.latitude!!, tab3googlemap.longitude!!)
                tab3googlemap.googleMap.addMarker(MarkerOptions().position(tab3googlemap.location).title("Location Marked"))
            }

        }
    }
}