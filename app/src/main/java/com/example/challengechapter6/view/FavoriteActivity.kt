package com.example.challengechapter6.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isEmpty
import com.example.challengechapter6.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        if (rv_list_film_favorite.isEmpty()){
            tv_film_favorite_kosong.setText("Daftar Kosong!")
        }
    }
}