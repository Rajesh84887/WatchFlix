package com.example.watchflix.network

import com.example.watchflix.network.data.Collection
import com.example.watchflix.network.data.Movie
import com.example.watchflix.network.data.Popular
import com.example.watchflix.network.data.Related
import com.example.watchflix.network.data.TVSeries
import com.example.watchflix.network.data.TopRated
import com.example.watchflix.network.data.UpComing
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CustomApi {
    @GET
    suspend fun getMovieLists(@Url url:String) : Response<TopRated>

    @GET
    suspend fun getUpComing(@Url url:String) : Response<UpComing>

    @GET
    suspend fun getMovieDetails(@Url url:String): Response<Movie>

    @GET
    suspend fun getCollection(@Url url:String): Response<Collection>

    @GET
    suspend fun getRelated(@Url url:String): Response<Related>

    @GET
    suspend fun getTVSeries(@Url url:String): Response<TVSeries>
}