package com.example.watchflix.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchflix.R
import com.example.watchflix.ui.adapters.RecyclerAdapter
import com.example.watchflix.ui.fragments.movies.ParentItem
import com.example.watchflix.viewmodel.CategoryViewModel

class Categories : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel:CategoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            prepareRecyclerView(view,it)
        })
    }
    fun prepareRecyclerView(view:View, data:ArrayList<ParentItem>){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(context,3)
        val adapter = RecyclerAdapter(data)
        recyclerView.adapter = adapter
    }
}