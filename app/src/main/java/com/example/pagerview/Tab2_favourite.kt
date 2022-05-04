package com.example.pagerview

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tab2_favourite : Fragment(),ViewpagerInterface{


    lateinit var recyclerView:RecyclerView
    var arrayOfObj= mutableListOf<Data>()
    lateinit var adapter:Myrecycleradapter
    lateinit var tvNorecord:TextView
    lateinit var intentFilter:IntentFilter
    lateinit var receiver:Receiverfor_tab2
    val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

        if (result?.resultCode == Activity.RESULT_OK)
        {
            tvNorecord.visibility=View.INVISIBLE
            val intent=result.data
            val position=intent?.getIntExtra(POSITION,-1).toString().toInt()
            val name:String=intent?.getStringExtra(NAME) as String
            val phoneNumber:String=intent.getStringExtra(PHONE_NUMBER) as String
            val latitude=intent?.getDoubleExtra(LATITUDE,-1.0).toString().toDouble()
            val longitude=intent?.getDoubleExtra(LONGITUDE,-1.0).toString().toDouble()
            var obj=Data(name,phoneNumber,latitude,longitude)
            if (position!=-1)
                arrayOfObj.set(position,obj)
            else
                arrayOfObj.add(obj)
            adapter=Myrecycleradapter(context,arrayOfObj,this,tvNorecord)
            recyclerView.adapter=adapter
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_2, container, false)
        tvNorecord=view.findViewById(R.id.tvNorecord)
        recyclerView=view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(context)

        receiver= Receiverfor_tab2(this)
        intentFilter=IntentFilter()
        intentFilter.addAction(BROAD_CAST1)
        context?.registerReceiver(receiver,intentFilter)
        return view
    }
    override fun getIntentShifter(shifter: Intent) {
        getContent.launch((shifter))
    }

    override fun dataPass(position: Int) {
        val intent:Intent= Intent()
        val name:String=arrayOfObj[position].name
        val phoneNumber:String=arrayOfObj[position].phoneNumber
        val latitude:Double=arrayOfObj[position].latitude
        val longitude:Double=arrayOfObj[position].longitude
        intent.putExtra(LATITUDE,latitude)
        intent.putExtra(LONGITUDE,longitude)
        intent.putExtra(NAME,name)
        intent.putExtra(PHONE_NUMBER,phoneNumber)
        intent.setAction(BROAD_CAST2)
        Toast.makeText(context, SUCCESSFULL,Toast.LENGTH_SHORT).show()
        context?.sendBroadcast(intent)
        arrayOfObj.removeAt(position)
        adapter.notifyDataSetChanged()
        if (arrayOfObj.size==0)
            tvNorecord.visibility=View.VISIBLE

    }
    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(receiver)
    }

}