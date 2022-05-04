package com.example.pagerview

import android.content.Intent

interface ViewpagerInterface {
//    companion object {
//        val obj:MyBroadCast=MyBroadCast()
//    }
    fun getIntentShifter(shifter:Intent)
    fun dataPass(position:Int)
}