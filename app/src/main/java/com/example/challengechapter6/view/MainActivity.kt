package com.example.challengechapter6.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.challengechapter6.R

class MainActivity : AppCompatActivity() {

    lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            sp = getSharedPreferences("ChallengeChapter6", Context.MODE_PRIVATE)

            if (getSharedPreferences("ChallengeChapter6", Context.MODE_PRIVATE).contains("email")
                && getSharedPreferences("ChallengeChapter6", Context.MODE_PRIVATE).contains("password")){
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }, 3000)

    }
}