package com.example.challengechapter6.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter6.R
import com.example.challengechapter6.viewmodel.ViewModelRegister
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener {
            val password = et_register_password.text.toString()
            val konfirmasi_password = et_register_konfirmasi_password.text.toString()

            if (password != konfirmasi_password){
                Toast.makeText(this, "Password Yang Anda Masukkan Tidak Sama!", Toast.LENGTH_LONG).show()
            }else{
                registerUser()
            }
        }

    }

    fun registerUser(){
        val username = et_register_username.text.toString()
        val email = et_register_email.text.toString()
        val password = et_register_password.text.toString()
        val viewModel = ViewModelProvider(this).get(ViewModelRegister::class.java)

        viewModel.getLiveDataRegister().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Berhasil Melakukan Register", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this, "Gagal Melakukan Register", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.registerDataUser(email,password,username)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}