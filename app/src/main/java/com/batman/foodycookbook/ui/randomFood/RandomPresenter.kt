package com.batman.foodycookbook.ui.randomFood

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.batman.foodycookbook.model.Meal
import com.batman.foodycookbook.model.MealSearch
import com.batman.foodycookbook.model.RandomResponse
import com.batman.foodycookbook.network.RetrofitObject
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomPresenter(var iRandomView: IRandomView, var context:Context): IRandomPresenter {

    val builder = GsonBuilder()
    val gson = builder.serializeNulls().create()
    private lateinit var productList : Array<Meal>
    private lateinit var searchList: Array<MealSearch>
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    override fun randomFoodApi() {

        if (iRandomView.isNetworkConnected()) {
            iRandomView.showLoading()

            RetrofitObject.instance.random().enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    if (response.code() == 200) {
                        iRandomView.hideLoading()
                        var res =
                            gson.fromJson(response.body().toString(), RandomResponse::class.java)
                        productList = res.meals.toTypedArray()
                        myAdapter = RandomFoodAdapter(res)

                        iRandomView.onRandomSuccess(res)

                    }
                    else {
                        iRandomView.hideLoading()
                        iRandomView.showMessage("Something went wrong")
                    }

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    iRandomView.showMessage(t.message.toString())
                }
            })

        }
        else{
            iRandomView.showMessage("Please connect to the internet")
        }

        }


}