package com.example.pagerview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText

class Contactinformation : AppCompatActivity() {

    lateinit var mapFragment:SupportMapFragment
    lateinit var googleMap:GoogleMap
    var latitude:Double=0.0
    var longitude:Double=0.0
    lateinit var location:LatLng
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactinformation)
        mapFragment=supportFragmentManager.findFragmentById(R.id.googlemap) as SupportMapFragment

        mapFragment.getMapAsync {
            googleMap = it
            googleMap.setOnMapClickListener{
                googleMap.clear()
                latitude = it.latitude
                longitude = it.longitude
                location = LatLng(latitude, longitude)
                googleMap.addMarker(MarkerOptions().position(location).title("Location Marked"))
            }

        }


        val etName:TextInputEditText=findViewById(R.id.etName)
        val etPhonenumber:TextInputEditText=findViewById(R.id.etPhoneNo)
        val btnSave:Button=findViewById(R.id.btnSave)
        val position=intent.getIntExtra(POSITION,-1)
        val str_name=intent?.getStringExtra(NAME).toString()
        val str_phoneNumber=intent?.getStringExtra(PHONE_NUMBER).toString()
        if (str_name!="null" && str_phoneNumber!="null")
        {
            latitude=intent!!.getDoubleExtra(LATITUDE,0.0)
            longitude=intent!!.getDoubleExtra(LONGITUDE,0.0)
            etName.setText(str_name)
            etPhonenumber.setText(str_phoneNumber)
            location = LatLng(latitude, longitude)
            mapFragment.getMapAsync {
                googleMap = it
                googleMap.addMarker(MarkerOptions().position(location).title("Location Marked"))
            }
        }
        btnSave.setOnClickListener {
            val name=etName.text.toString()
            val phoneNumber=etPhonenumber.text.toString()
            if (name.isEmpty() && phoneNumber.isEmpty())
            {
                Toast.makeText(this,"Empty Field Not Allowed",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val shifter= Intent()
                shifter.putExtra(NAME,name)
                shifter.putExtra(PHONE_NUMBER,phoneNumber)
                shifter.putExtra(POSITION,position)
                shifter.putExtra(LATITUDE,latitude)
                shifter.putExtra(LONGITUDE,longitude)
                setResult(Activity.RESULT_OK,shifter)
                finish()
                Toast.makeText(this@Contactinformation, SUCCESSFULL,Toast.LENGTH_SHORT).show()
            }
        }

    }
}