package com.batman.foodycookbook.ui.randomFood

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batman.foodycookbook.BaseActivity
import com.batman.foodycookbook.R
import com.batman.foodycookbook.model.Meal
import com.batman.foodycookbook.model.RandomResponse
import com.batman.foodycookbook.model.SearchResponse
import com.batman.foodycookbook.ui.search.SearchActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_random_food.*

class RandomFoodActivity : BaseActivity(), IRandomView {

    private val randomPresenter = RandomPresenter(this,this)

    val builder = GsonBuilder()
    val gson = builder.serializeNulls().create()
    private lateinit var manager: RecyclerView.LayoutManager

    private lateinit var myAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_food)



        toolbarItem.title = ""
        setSupportActionBar(toolbarItem)
        // supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        manager = GridLayoutManager(this,1)
        randomPresenter.randomFoodApi()

        button.setOnClickListener {
            var text = searchview.text.toString().trim()
          //  showMessage(text)
            val intent = Intent(this,SearchActivity::class.java)
            intent.putExtra(SearchActivity.value,text)
            startActivity(intent)
        }



    }





    override fun onRandomSuccess(res: RandomResponse) {
        productRecycler.apply {
            myAdapter = RandomFoodAdapter(res)
            layoutManager = manager

            adapter = myAdapter
        }
    }


    }

