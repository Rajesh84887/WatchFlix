package com.example.watchflix.ui.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchflix.R
import com.example.watchflix.ui.adapters.ParentItemAdapter
import com.example.watchflix.viewmodel.MoviesListViewModel

class MoviesList : Fragment() {
    private val data = ArrayList<ParentItem>()
    private val childItem  = arrayOf(R.drawable.alpha,R.drawable.beta,R.drawable.cupcake,R.drawable.donut,R.drawable.eclair,R.drawable.froyo,R.drawable.gingerbread,R.drawable.honeycomb,R.drawable.icecreamsandwich,R.drawable.jellybean,R.drawable.kitkat,R.drawable.lollipop,R.drawable.marshmallow,R.drawable.nougat,R.drawable.oreo,R.drawable.pie)
    private val topData = ArrayList<ParentItemTop>()
    private val imageId = R.drawable.title_card
    private val title = "Blade Runner 2049"
    private val content = "Thirty years after the events of the first film, a new blade runner, LAPD Officer K (Ryan Gosling), unearths a long buried secret that has the potential to plunge what\'s left of society into chaos. K\'s discovery leads him on a quest to find Rick Deckard (Harrison Ford), a former LAPD blade runner who has been missing for 30 years."
    private val rating = 3.5f
    init {
//        for (i in 1..10){
//            data.add(ParentItem("Child $i", childItem))
//        }
        for (i in 1..4){
            topData.add(ParentItemTop(imageId,title,content,rating))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: MoviesListViewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            prepareRecyclerView(view,it)
        })

    }
    fun prepareRecyclerView(view:View, data:ArrayList<ParentItem>){
        val parentRecyclerView = view.findViewById<RecyclerView>(R.id.parent_recyclerview)
        parentRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ParentItemAdapter(data,topData)
        parentRecyclerView.adapter = adapter
    }
}