package com.batman.foodycookbook

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_random_food.*
import kotlinx.android.synthetic.main.activity_search.*

open class BaseActivity: AppCompatActivity() {

    fun isNetworkConnected() : Boolean {
        val connectivityManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo?.isConnectedOrConnecting == true
    }

    fun showMessage(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        progressBar?.visibility = View.VISIBLE
        progressBar_search?.visibility = View.VISIBLE
    }
    fun hideLoading(){
        progressBar_search?.visibility = View.GONE
        progressBar?.visibility = View.GONE
    }

}