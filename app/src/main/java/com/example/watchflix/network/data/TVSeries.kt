package com.example.watchflix.network.data

data class TVSeries(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)