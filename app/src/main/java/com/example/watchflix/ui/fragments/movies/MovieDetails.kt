package com.example.watchflix.ui.fragments.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchflix.R
import com.example.watchflix.SecondActivity
import com.example.watchflix.ui.adapters.MovieDetailAdapter
import com.example.watchflix.viewmodel.MovieDetailsViewModel

class MovieDetails : Fragment() {
    private val childItem  = arrayOf(R.drawable.alpha,R.drawable.beta,R.drawable.cupcake,R.drawable.donut,R.drawable.eclair,R.drawable.froyo,R.drawable.gingerbread,R.drawable.honeycomb,R.drawable.icecreamsandwich,R.drawable.jellybean,R.drawable.kitkat,R.drawable.lollipop,R.drawable.marshmallow,R.drawable.nougat,R.drawable.oreo,R.drawable.pie)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id")
        val title = arguments?.getString("title")
        val poster = "https://image.tmdb.org/t/p/w220_and_h330_face"+arguments?.getString("poster")
        val rating = arguments?.getDouble("rating")
        val releaseDate = arguments?.getString("releaseDate")
        val overview = arguments?.getString("overview")

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(poster)
            .placeholder(R.mipmap.ic_launcher)
            .error(android.R.drawable.ic_menu_camera)
            .into(imageView)
        view.findViewById<TextView>(R.id.titleView).text = title
        view.findViewById<TextView>(R.id.ratingView).text = rating.toString()
        view.findViewById<TextView>(R.id.releaseView).text = releaseDate
        view.findViewById<TextView>(R.id.textView1).text = overview

        val playButton = view.findViewById<ImageButton>(R.id.playButton)
        playButton.setOnClickListener{
            Log.d("Play Button Check","Click")
//            Navigation.findNavController(it).navigate(R.id.action_movieDetails_to_videoFragment)
            val intent = Intent(this@MovieDetails.requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        val viewModel: MovieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        viewModel.getDataFromApi(id!!)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            prepareRecyclerView(view,it)
        })

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("check","Back Button Clicked")
                Navigation.findNavController(view).navigateUp()
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    fun prepareRecyclerView(view:View,childItem: ArrayList<ChildItem>){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL,false)
        val adapter = MovieDetailAdapter(childItem)
        recyclerView.adapter = adapter
    }
}