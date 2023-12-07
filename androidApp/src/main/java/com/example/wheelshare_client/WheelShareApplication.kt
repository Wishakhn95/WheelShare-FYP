package com.example.wheelshare_client

import android.app.Application
import com.example.wheelshare_client.di.initKoin

class WheelShareApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

}