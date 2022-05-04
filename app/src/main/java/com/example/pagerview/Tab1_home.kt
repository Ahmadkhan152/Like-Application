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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Tab1_home : Fragment(),ViewpagerInterface {
    lateinit var tvNorecord:TextView
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:Myrecycleradapter
    lateinit var receiver:Receiverfor_tab1
    lateinit var intentFilter:IntentFilter
    val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

        if (result?.resultCode == Activity.RESULT_OK)
        {
            tvNorecord.visibility=View.INVISIBLE
            val intent=result.data
            val position=intent?.getIntExtra(POSITION,-1).toString().toInt()
            val latitude=intent?.getDoubleExtra(LATITUDE,-1.0).toString().toDouble()
            val longitude=intent?.getDoubleExtra(LONGITUDE,-1.0).toString().toDouble()
            val check=intent?.getBooleanExtra(CHECK,false).toString().toBoolean()
            val name:String=intent?.getStringExtra(NAME) as String
            val phoneNumber:String=intent.getStringExtra(PHONE_NUMBER) as String
            var obj=Data(name,phoneNumber,latitude,longitude)
            if (position!=-1)
            {
                arrayOfObj.set(position,obj)
                Receiverfor_tab3.arrayOfObj=arrayOfObj
                val intent:Intent= Intent()
                intent.putExtra(NAME,"-1")
                intent.putExtra(PHONE_NUMBER,"-1")
                intent.putExtra(POSITION,position)
                intent.setAction(BROAD_CAST3)
                context?.sendBroadcast(intent)
            }
            else
            {
                arrayOfObj.add(obj)
                intent.setAction(BROAD_CAST3)
                context?.sendBroadcast(intent)
            }
            adapter=Myrecycleradapter(context,arrayOfObj,this,tvNorecord)
            recyclerView.adapter=adapter
        }
    }
    var arrayOfObj= mutableListOf<Data>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_1, container, false)
        val btnAdd:FloatingActionButton=view.findViewById(R.id.btnfloat)
        tvNorecord=view.findViewById(R.id.tvNorecord)
        recyclerView=view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(context)

        btnAdd.setOnClickListener {
            val shifter= Intent(context,Contactinformation::class.java)
            getContent.launch(shifter)
        }

        receiver= Receiverfor_tab1(this@Tab1_home)
        intentFilter=IntentFilter()
        intentFilter.addAction(BROAD_CAST2)
        context?.registerReceiver(receiver,intentFilter)
        return view;
    }
    override fun dataPass(position:Int)
    {
        val intent:Intent= Intent()
        val name:String=arrayOfObj[position].name
        val phoneNumber:String=arrayOfObj[position].phoneNumber
        val latitude:Double=arrayOfObj[position].latitude
        val longitude:Double=arrayOfObj[position].longitude
        intent.putExtra(LATITUDE,latitude)
        intent.putExtra(LONGITUDE,longitude)
        intent.putExtra(NAME,name)
        intent.putExtra(PHONE_NUMBER,phoneNumber)
        intent.setAction(BROAD_CAST1)
        context?.sendBroadcast(intent)
        intent.setAction(BROAD_CAST3)
        context?.sendBroadcast(intent)
        Toast.makeText(context, SUCCESSFULL,Toast.LENGTH_SHORT).show()
        arrayOfObj.removeAt(position)
        adapter.notifyDataSetChanged()
        if (arrayOfObj.size==0)
            tvNorecord.visibility=View.VISIBLE

    }
    override fun getIntentShifter(shifter:Intent) {
        getContent.launch(shifter)
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(receiver)
    }
}