package com.example.watchflix.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchflix.R
import com.example.watchflix.ui.fragments.movies.ParentItem

class RecyclerAdapter(private val data: ArrayList<ParentItem>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parentItem = data[position]
        val childItem = parentItem.childItem
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w220_and_h330_face"+childItem[0].image)
            .placeholder(R.mipmap.ic_launcher)
            .error(android.R.drawable.ic_menu_camera)
            .into(holder.imageView)
        holder.title.text = parentItem.parentItemTitle
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val title:TextView = itemView.findViewById(R.id.titleView)
    }
}