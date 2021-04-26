package com.batman.foodycookbook.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batman.foodycookbook.BaseActivity
import com.batman.foodycookbook.R
import com.batman.foodycookbook.model.MealSearch
import com.batman.foodycookbook.model.RandomResponse
import com.batman.foodycookbook.model.SearchResponse
import com.batman.foodycookbook.network.RetrofitObject
import com.batman.foodycookbook.ui.randomFood.RandomFoodActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

import kotlinx.android.synthetic.main.activity_random_food.*
import kotlinx.android.synthetic.main.activity_random_food.toolbarItem
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : BaseActivity(), ISearchView {

    private val searchPresenter = SearchPresenter(this,this)

    lateinit var manager: RecyclerView.LayoutManager
    lateinit var myAdapter: RecyclerView.Adapter<*>

    companion object {
        const val value = "VALUE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        toolbarItem.title = ""
        setSupportActionBar(toolbarItem)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        manager = GridLayoutManager(this,1)
        var searchValue = intent.getStringExtra(value).toString()

        showMessage(searchValue!!)

        searchPresenter.searchAPI(searchValue)

    }



    override fun onSearchSuccess(res: SearchResponse) {
        searchRecycler.apply {
            myAdapter = RandomSearchAdapter(res)
            layoutManager = manager

            adapter = myAdapter
        }
    }

    override fun onSearchError() {
        startActivity(Intent(this,RandomFoodActivity::class.java))
        finish()
    }

}