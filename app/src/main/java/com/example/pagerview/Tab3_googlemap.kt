package com.example.pagerview

import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class Tab3_googlemap : Fragment() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    var latitude:Double?=null
    var longitude:Double?=null
    lateinit var location: LatLng
    lateinit var receiver:Receiverfor_tab3
    lateinit var intentFilter: IntentFilter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.fragment_googlemapfragment, container, false)
        mapFragment=childFragmentManager.findFragmentById(R.id.googlemap) as SupportMapFragment
        receiver= Receiverfor_tab3(this)
        intentFilter = IntentFilter()
        intentFilter.addAction(BROAD_CAST3)
        context?.registerReceiver(receiver,intentFilter)
        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(receiver)
    }
}