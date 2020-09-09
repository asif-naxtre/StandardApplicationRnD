package com.rahat.standardapplicationrnd

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class StandardApplicationRnD : Application(){

    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)
    }
}