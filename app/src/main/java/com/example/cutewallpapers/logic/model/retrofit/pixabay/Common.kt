package com.example.cutewallpapers.logic.model.retrofit.pixabay

object Common {
    private const val BASE_URL = "https://pixabay.com/"
    const val KEY = "33106230-b104905cd7ff74ed17e2229af"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}