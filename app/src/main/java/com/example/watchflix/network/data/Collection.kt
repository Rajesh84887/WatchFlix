package com.example.watchflix.network.data

data class Collection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val overview: String,
    val parts: List<Part>,
    val poster_path: String
)