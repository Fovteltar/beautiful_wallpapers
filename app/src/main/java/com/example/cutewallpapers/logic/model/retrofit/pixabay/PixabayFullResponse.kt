package com.example.cutewallpapers.logic.model.retrofit.pixabay

data class PixabayFullResponse(
    val hits: List<PixabayHit>,
    val total: Int,
    val totalHits: Int
)