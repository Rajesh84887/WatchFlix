package com.example.watchflix.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.watchflix.R
import com.example.watchflix.ui.fragments.movies.ParentItem
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val data: ParentItem) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_slider_item, parent, false)
        return SliderAdapterVH(view)
    }

    override fun onBindViewHolder(holder: SliderAdapterVH, position: Int) {
        val childItem = data.childItem.get(position)
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w220_and_h330_face"+childItem.poster)
            .placeholder(R.mipmap.ic_launcher)
            .error(android.R.drawable.ic_menu_camera)
            .into(holder.image)
        holder.title.text = childItem.title
        holder.content.text = childItem.overview
        holder.rating.rating = (childItem.rating/2).toFloat()
    }

    override fun getCount(): Int {
        return data.childItem.size
    }

    class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.imageView)
        var title = itemView.findViewById<TextView>(R.id.titleView)
        var content = itemView.findViewById<TextView>(R.id.contentView)
        var rating = itemView.findViewById<RatingBar>(R.id.ratingBar)
        var Watch = itemView.findViewById<Button>(R.id.button)
    }
}