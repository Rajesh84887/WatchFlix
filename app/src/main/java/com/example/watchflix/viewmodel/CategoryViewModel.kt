package com.example.watchflix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchflix.network.RetrofitHelper
import com.example.watchflix.ui.fragments.movies.ChildItem
import com.example.watchflix.ui.fragments.movies.ParentItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryViewModel:ViewModel() {
    private val result = ArrayList<ParentItem>()
    val data = MutableLiveData<ArrayList<ParentItem>>()
    private val genreList = listOf(28,12,16,35,80,99,18,10751,14,36,27,10402,9648,10749,878,10770,53,10752,37)
    private val genreListTitle = listOf("Action","Adventure","Animation","Comedy","Crime","Documentary","Drama","Family","Fantasy","History","Horror","Music","Mystery","Romance","Science Fiction","TV Movie","Thriller","War","Western")

    init {
        getDataFromApi()
    }

    fun getDataFromApi(){
        GlobalScope.launch(Dispatchers.IO) {
            fetchGenreDetails()

            data.postValue(result)
        }
    }

    suspend fun fetchGenreDetails(){
        for (i in 0..genreList.size-1){
            val apiData = RetrofitHelper.api.getRelated("discover/movie?with_genres=${genreList[i]}&api_key=56c49c7050604cbba16a4c9c84594633")
            Log.d("API Response ${genreListTitle[i]} Ca",apiData?.body()?.results.toString())
            val apiResult = apiData?.body()?.results
            val child = ArrayList<ChildItem>()
            apiResult?.let {
                for(i in 0..apiResult!!.size-1){
                    child.add(ChildItem(apiResult!!.get(i).id,apiResult!!.get(i)?.title,apiResult!!.get(i)?.poster_path,apiResult!!.get(i)?.backdrop_path,apiResult!!.get(i).vote_average,apiResult!!.get(i)?.release_date,apiResult!!.get(i)?.overview))
                }
            }
            result.add(ParentItem("${genreListTitle[i]}",child))
        }
    }
}