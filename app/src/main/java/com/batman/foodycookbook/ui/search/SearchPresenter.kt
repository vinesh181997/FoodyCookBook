package com.batman.foodycookbook.ui.search

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.batman.foodycookbook.model.SearchResponse
import com.batman.foodycookbook.network.RetrofitObject
import com.batman.foodycookbook.ui.randomFood.RandomFoodActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(var iSearchView: ISearchView,var context: Context):ISearchPresenter {
    override fun searchAPI(searchValue: String) {

        val builder = GsonBuilder()
        val gson = builder.serializeNulls().create()
        lateinit var manager: RecyclerView.LayoutManager
        lateinit var myAdapter: RecyclerView.Adapter<*>


        RetrofitObject.instance.search(searchValue)
                .enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                        iSearchView.hideLoading()

                        if (response.code()== 200){

                            Log.d("BATMAN","response")

                            var searchres = gson.fromJson(response.body().toString(), SearchResponse::class.java)
                            //   searchList = searchres.meals.toTypedArray()
                            if (searchres.meals.isNullOrEmpty()){
                                iSearchView.showMessage("No items found")
                                iSearchView.onSearchError()
                          }
                            else {
                                myAdapter = RandomSearchAdapter(searchres)

                                iSearchView.onSearchSuccess(searchres)

                            }

                        }

                        else{
                            iSearchView.showMessage("No Items found")
                            Log.d("else","statement")
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.d("BATMAN", "NO RESPONSE")
                        iSearchView.showMessage(t.message.toString())
                    }

                })

    }
}