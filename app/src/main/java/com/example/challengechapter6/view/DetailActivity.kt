package com.example.challengechapter6.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.challengechapter6.R
import com.example.challengechapter6.model.GetAllFilmResponseItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailFilm = intent.getParcelableExtra<GetAllFilmResponseItem>("DETAIL_FILM")

        tv_detail_judul_film.text = detailFilm?.title
        tv_detail_tahun_film.text = detailFilm?.releaseDate
        tv_detail_sutradara_film.text = detailFilm?.director
        tv_detail_deskripsi_film.text = detailFilm?.synopsis
        Glide.with(this).load(detailFilm?.image).into(siv_detail_gambar_film)

    }
}