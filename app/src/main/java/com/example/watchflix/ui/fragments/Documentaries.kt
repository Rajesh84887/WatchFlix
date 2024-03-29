package com.example.watchflix.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchflix.R
import com.example.watchflix.ui.adapters.DocumentaryAdapter
import com.example.watchflix.ui.fragments.movies.ChildItem
import com.example.watchflix.viewmodel.DocumentaryViewModel

class Documentaries : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_documentaries, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: DocumentaryViewModel = ViewModelProvider(this).get(DocumentaryViewModel::class.java)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            prepareRecyclerView(view,it)
        })
    }
    fun prepareRecyclerView(view:View, data:ArrayList<ChildItem>){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(context,3)
        val adapter = DocumentaryAdapter(data)
        recyclerView.adapter = adapter
    }
}