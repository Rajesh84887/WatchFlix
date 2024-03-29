package com.example.watchflix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchflix.network.RetrofitHelper
import com.example.watchflix.ui.fragments.movies.ChildItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DocumentaryViewModel : ViewModel() {
    private val result = ArrayList<ChildItem>()
    val data = MutableLiveData<ArrayList<ChildItem>>()

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
        val apiData = RetrofitHelper.api.getRelated("discover/movie?with_genres=99&api_key=56c49c7050604cbba16a4c9c84594633")
        Log.d("API Response Related",apiData?.body()?.results.toString())
        val apiResult = apiData?.body()?.results
        apiResult?.let {
            for(i in 0..apiResult!!.size-1){
                result.add(ChildItem(apiResult!!.get(i).id,apiResult!!.get(i)?.title,apiResult!!.get(i)?.poster_path,apiResult!!.get(i)?.backdrop_path,apiResult!!.get(i).vote_average,apiResult!!.get(i)?.release_date,apiResult!!.get(i)?.overview))
            }
        }
    }
}