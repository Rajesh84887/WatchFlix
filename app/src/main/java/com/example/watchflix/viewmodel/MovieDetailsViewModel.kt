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

class MovieDetailsViewModel : ViewModel() {
    private val result = ArrayList<ChildItem>()
    val data = MutableLiveData<ArrayList<ChildItem>>()


    fun getDataFromApi(id:Int){
        GlobalScope.launch(Dispatchers.IO) {
            val apiData = RetrofitHelper.api.getMovieDetails("movie/$id?api_key=56c49c7050604cbba16a4c9c84594633")
            Log.d("APIResponse MovieDetail",apiData?.body()?.genres.toString())

            //Get movie details from collections
            val collectionId = apiData.body()?.belongs_to_collection?.id
            fetchCollectionDetails(collectionId)

            //Get movie details from Genres
            val genres = ArrayList<Int>()
            for(i in 0..apiData.body()?.genres!!.size-1){
                genres.add(apiData.body()?.genres?.get(i)!!.id)
            }
            fetchGenreDetails(genres)

            data.postValue(result)
        }
    }

    suspend fun fetchCollectionDetails(collectionId:Int?){
        val apiData = RetrofitHelper.api.getCollection("collection/$collectionId?api_key=56c49c7050604cbba16a4c9c84594633")
        Log.d("API Response Collection",apiData?.body()?.parts.toString())
        val resultData = apiData?.body()?.parts
        resultData?.let {
            for (i in 0..resultData!!.size-1){
                result.add(ChildItem(resultData!!.get(i).id,resultData!!.get(i)?.title,resultData!!.get(i)?.poster_path,resultData!!.get(i)?.backdrop_path,resultData!!.get(i).vote_average,resultData!!.get(i)?.release_date,resultData!!.get(i)?.overview))
            }
        }
    }
    suspend fun fetchGenreDetails(genres:ArrayList<Int>){
        for (i in 0..genres.size-1){
            val apiData = RetrofitHelper.api.getRelated("discover/movie?with_genres=${genres[i]}&api_key=56c49c7050604cbba16a4c9c84594633")
            Log.d("API Response Related MD",apiData?.body()?.results.toString())
            val apiResult = apiData?.body()?.results
            apiResult?.let {
                for(i in 0..apiResult!!.size-1){
                    result.add(ChildItem(apiResult!!.get(i).id,apiResult!!.get(i)?.title,apiResult!!.get(i)?.poster_path,apiResult!!.get(i)?.backdrop_path,apiResult!!.get(i).vote_average,apiResult!!.get(i)?.release_date,apiResult!!.get(i)?.overview))
                }
            }
        }
    }
}