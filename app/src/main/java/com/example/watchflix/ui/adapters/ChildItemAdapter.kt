package com.example.watchflix.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchflix.R
import com.example.watchflix.ui.fragments.movies.ChildItem

class ChildItemAdapter(private val data: List<ChildItem>): RecyclerView.Adapter<ChildItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_child_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val childItem = data[position]
        //holder.imageView.setImageResource(childItem)
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w220_and_h330_face"+childItem.image)
            .placeholder(R.mipmap.ic_launcher)
            .error(android.R.drawable.ic_menu_camera)
            .into(holder.imageView)
        holder.imageView.setOnClickListener{
            val id = childItem.id
            val title = childItem.title
            val poster = childItem.poster
            val rating = childItem.rating
            val releaseDate = childItem.releaseDate
            val overview = childItem.overview
            val bundle = Bundle()
            bundle.putInt("id",id)
            bundle.putString("title",title)
            bundle.putString("poster",poster)
            bundle.putDouble("rating",rating)
            bundle.putString("releaseDate",releaseDate)
            bundle.putString("overview",overview)
            Navigation.findNavController(holder.itemView).navigate(R.id.action_moviesList_to_movieDetails,bundle)
        }
    }

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
    }
}