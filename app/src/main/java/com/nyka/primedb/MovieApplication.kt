package com.nyka.primedb

import android.app.Application
import com.google.android.material.color.DynamicColors

class MovieApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}