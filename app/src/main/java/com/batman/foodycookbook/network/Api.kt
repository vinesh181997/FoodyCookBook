package com.batman.foodycookbook.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("random.php")
    fun random(): Call<JsonObject>

//    @FormUrlEncoded
//    @POST("search.php?")
//    fun search(
//        @Field("s") search:String
//    ):Call<JsonObject>

    @GET("search.php?")
    fun search(
            @Query("s") s: String
    ) : Call<JsonObject>

}