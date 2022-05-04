package com.example.pagerview

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class Myrecycleradapter(val context: Context?,val arrayOfObj:MutableList<Data>,val obj:ViewpagerInterface,val tvNorecord:TextView) : RecyclerView.Adapter<Myrecycleradapter.viewholder>() {


    class viewholder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val tvName: TextView =itemView.findViewById(R.id.tvName)
        val tvPhonenumber:TextView=itemView.findViewById(R.id.tvPhonenumber)
        val btnLike:ImageView=itemView.findViewById(R.id.btnLike)
        val btnEdit:ImageView=itemView.findViewById(R.id.btnEdit)
        val btnDelete:ImageView=itemView.findViewById(R.id.btnDelete)
        val tvLatitude:TextView=itemView.findViewById(R.id.tvlatitude)
        val tvLongitude:TextView=itemView.findViewById(R.id.tvlongitude)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        var myView= LayoutInflater.from(context).inflate(R.layout.viewlayout,parent,false)
        return viewholder(myView);
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.tvName.text=arrayOfObj[position].name
        holder.tvPhonenumber.text=arrayOfObj[position].phoneNumber
        holder.tvLatitude.text= arrayOfObj[position].latitude.toString()
        holder.tvLongitude.text=arrayOfObj[position].longitude.toString()
        holder.btnDelete.setOnClickListener {

            arrayOfObj.removeAt(position)
            Receiverfor_tab3.arrayOfObj=arrayOfObj
            notifyDataSetChanged()
            val intent:Intent= Intent()
            intent.putExtra(NAME,"-1")
            intent.putExtra(PHONE_NUMBER,"-1")
            intent.putExtra(POSITION,position)
            intent.setAction(BROAD_CAST3)
            context?.sendBroadcast(intent)
            if (arrayOfObj.size==0)
            {
                tvNorecord.visibility=View.VISIBLE
                Toast.makeText(context, NO_RECORD,Toast.LENGTH_SHORT).show()
            }

        }
        holder.btnEdit.setOnClickListener {

            val shifter= Intent(context,Contactinformation::class.java)
            shifter.putExtra(POSITION,position)
            shifter.putExtra(NAME,arrayOfObj[position].name)
            shifter.putExtra(PHONE_NUMBER,arrayOfObj[position].phoneNumber)
            shifter.putExtra(LATITUDE,arrayOfObj[position].latitude)
            shifter.putExtra(LONGITUDE,arrayOfObj[position].longitude)
            shifter.putExtra(CHECK,true)
            obj.getIntentShifter(shifter)
        }
        holder.btnLike.setOnClickListener {
            obj.dataPass(position)
        }

    }

    override fun getItemCount(): Int {
        return arrayOfObj.size
    }

}
