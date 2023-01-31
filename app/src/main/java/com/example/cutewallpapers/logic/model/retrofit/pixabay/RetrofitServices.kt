package com.example.cutewallpapers.logic.model.retrofit.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("/api/")
    fun getImageList(@Query("key") key: String, @Query("category") category: String):
        Call<PixabayFullResponse>
}