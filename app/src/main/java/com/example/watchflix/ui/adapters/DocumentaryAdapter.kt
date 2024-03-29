package com.example.watchflix.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchflix.R
import com.example.watchflix.ui.fragments.movies.ChildItem

class DocumentaryAdapter(private val data: ArrayList<ChildItem>) : RecyclerView.Adapter<DocumentaryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.documentaries_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("document",data.size.toString())
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val childItem = data[position]
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w220_and_h330_face"+childItem.image)
            .placeholder(R.mipmap.ic_launcher)
            .error(android.R.drawable.ic_menu_camera)
            .into(holder.imageView)
        holder.title.text = childItem.title
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val title: TextView = itemView.findViewById(R.id.titleView)
    }
}