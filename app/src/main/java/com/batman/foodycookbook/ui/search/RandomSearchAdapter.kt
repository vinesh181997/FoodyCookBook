package com.batman.foodycookbook.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batman.foodycookbook.R
import com.batman.foodycookbook.model.MealSearch
import com.batman.foodycookbook.model.SearchResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

class RandomSearchAdapter(private val data:SearchResponse) : RecyclerView.Adapter<RandomSearchAdapter.MyViewHolder>() {


        class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {

            fun bind(productData: MealSearch) {
                view.foodName_id.text =  productData.strMeal
                Glide.with(view.context).load(productData.strMealThumb).centerCrop().into(view.food_img_id)

            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return MyViewHolder(v)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(data.meals[position])

        }

        override fun getItemCount(): Int {
            return data.meals.size
        }
    }
