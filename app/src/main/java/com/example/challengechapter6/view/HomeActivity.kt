package com.example.challengechapter6.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter6.R
import com.example.challengechapter6.adapter.AdapterFilm
import com.example.challengechapter6.viewmodel.ViewModelFilm
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var filmAdapter : AdapterFilm
    lateinit var shpr : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        shpr = getSharedPreferences("ChallengeChapter6", Context.MODE_PRIVATE)
        tv_home.text = "Hello, " + shpr.getString("email", "")

        initRecyclerView()
        initViewModel()

        iv_home_favorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        iv_home_akun.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        if (initRecyclerView() == null){
            tv_film_kosong.setText("Daftar Kosong!")
        }

    }

    fun initRecyclerView(){
        rv_list_film.layoutManager = LinearLayoutManager(this)
        filmAdapter = AdapterFilm(){
            val pindah = Intent(this, DetailActivity::class.java)
            pindah.putExtra("DETAIL_FILM", it)
            startActivity(pindah)
        }
        rv_list_film.adapter = filmAdapter
    }

    fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.getLiveDataFilm().observe(this, Observer {
            if (it != null){
                filmAdapter.setFilmList(it)
                filmAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getDataFilm()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}