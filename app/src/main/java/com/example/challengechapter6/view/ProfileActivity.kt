package com.example.challengechapter6.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.challengechapter6.R
import com.example.challengechapter6.model.UserDetails
import com.example.challengechapter6.viewmodel.ViewModelProfile
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var listUser: List<UserDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sharedPreferences = getSharedPreferences("ChallengeChapter6", Context.MODE_PRIVATE)
        getDataUserProfile()

        btn_profile_update.setOnClickListener {
            updateDataProfileUser()
            finish()
        }

        btn_profile_logout.setOnClickListener {
            logout()
        }

    }

    fun getDataUserProfile(){
        val id = sharedPreferences.getString("id", "")
        val viewModel = ViewModelProvider(this).get(ViewModelProfile::class.java)
        viewModel.detailUserProfile(id!!.toInt())
        viewModel.getLiveDataDetailUser().observe(this, Observer {
            if (it != null){
                listUser = it
                initDataUser(listUser)
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Gagal Menampilkan Profile User!" , Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initDataUser(detailListUser: List<UserDetails>){
        for (i in detailListUser.indices){
            et_profile_nama_lengkap.setText(detailListUser[i].completeName)
            et_profile_username.setText(detailListUser[i].username)
            et_profile_alamat.setText(detailListUser[i].address)
            et_profile_tanggal_lahir.setText(detailListUser[i].dateofbirth)
        }
    }

    fun updateDataProfileUser(){
        val id = sharedPreferences.getString("id", "")
        val compname = et_profile_nama_lengkap.text.toString()
        val uname = et_profile_username.text.toString()
        val add = et_profile_alamat.text.toString()
        val birth = et_profile_tanggal_lahir.text.toString()

        val viewModel = ViewModelProvider(this).get(ViewModelProfile::class.java)
        viewModel.getLiveDataUpdate().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Gagal Update Data User!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Berhasil Update Data User!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        })
        viewModel.updateUserProfile(id!!.toInt(),compname,uname,add,birth)
    }

    fun logout(){
        AlertDialog.Builder(this)
            .setTitle("Keluar Aplikasi")
            .setMessage("Yakin ingin keluar aplikasi?")
            .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                sharedPreferences = getSharedPreferences("ChallengeChapter6", Context.MODE_PRIVATE)
                val sp = sharedPreferences.edit()
                sp.clear()
                sp.apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }

}