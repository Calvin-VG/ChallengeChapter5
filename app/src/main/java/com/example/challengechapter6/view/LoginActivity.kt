package com.example.challengechapter6.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter6.R
import com.example.challengechapter6.model.UserResponse
import com.example.challengechapter6.viewmodel.ViewModelLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharepref : SharedPreferences
    lateinit var listUserLogin : List<UserResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val email = et_login_email.text.toString()
            val password = et_login_password.text.toString()

            if (email.isEmpty()){
                et_login_email.setError("Masukkan Email!")
            }else if (password.isEmpty()){
                et_login_password.setError("Masukkan Password!")
            }else{
                loginUser()
            }
        }

        tv_login_buat_akun.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    fun loginUser(){
        val viewModel = ViewModelProvider(this).get(ViewModelLogin::class.java)
        viewModel.loginUser()
        viewModel.getLiveDataLogin().observe(this, Observer {
            if (it != null){
                listUserLogin = it
                loginAuth(listUserLogin)
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                Toast.makeText(this, "Gagal Melakukan Login!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun loginAuth(listLogin: List<UserResponse>){
        val email = et_login_email.text.toString()
        val password = et_login_password.text.toString()

        for(i in listLogin.indices){
            if (email == listLogin[i].email && password == listLogin[i].password) {
                sharepref = getSharedPreferences("ChallengeChapter6", Context.MODE_PRIVATE)
                val sfe = sharepref.edit()
                sfe.putString("email", listLogin[i].email).apply()
                sfe.putString("password", listLogin[i].password).apply()
                sfe.putString("id", listLogin[i].id).apply()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}