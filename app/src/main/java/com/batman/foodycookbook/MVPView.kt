package com.batman.foodycookbook

interface MVPView {

    fun isNetworkConnected() : Boolean
    fun showMessage(message:String)
    fun showLoading()
    fun hideLoading()
}