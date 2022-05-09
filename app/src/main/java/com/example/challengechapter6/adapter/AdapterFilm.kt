package com.example.challengechapter6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter6.R
import com.example.challengechapter6.model.GetAllFilmResponseItem
import kotlinx.android.synthetic.main.item_adapter_film.view.*

class AdapterFilm(private var onClick :(GetAllFilmResponseItem)->Unit): RecyclerView.Adapter<AdapterFilm.ViewHolder>() {

    private var filmData : List<GetAllFilmResponseItem>? = null

    fun setFilmList(filmList: List<GetAllFilmResponseItem>){
        this.filmData = filmList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFilm.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter_film, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterFilm.ViewHolder, position: Int) {
        holder.itemView.tv_item_judul_film.text = filmData!![position].title
        holder.itemView.tv_item_tanggal_rilis_film.text = filmData!![position].releaseDate
        holder.itemView.tv_item_sutradara_film.text = filmData!![position].director

        Glide.with(holder.itemView.context).load(filmData!![position].image).into(holder.itemView.siv_item_gambar)

        holder.itemView.cv_list_film.setOnClickListener {
            onClick(filmData!![position])
        }
    }

    override fun getItemCount(): Int {
        if (filmData == null){
            return 0
        }else{
            return filmData!!.size
        }
    }
}