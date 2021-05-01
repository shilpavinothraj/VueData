package com.example.vuedate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    val SPLASH_TIME_OUT = 1000

    var i: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        i = Intent(this, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}