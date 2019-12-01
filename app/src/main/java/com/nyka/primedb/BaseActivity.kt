package com.nyka.primedb

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    val apiRoute = "https://api.themoviedb.org"
    val apiKey = "1469231605651a4f67245e5257160b5f"


    fun hideStatusBarBackground(){
        window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
    }

}