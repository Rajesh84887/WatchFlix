package com.example.watchflix.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchflix.R
import com.example.watchflix.ui.fragments.movies.ParentItem
import com.example.watchflix.ui.fragments.movies.ParentItemTop
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class ParentItemAdapter(private val data: ArrayList<ParentItem>, private val topData:ArrayList<ParentItemTop>): RecyclerView.Adapter<ParentItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = if(viewType==0){
            R.layout.movies_parent_item_top
        }else{
            R.layout.movies_parent_item
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parentItem = data[position]
        if(position==0){
            holder.bindSlider(parentItem)
        }else{
            holder.bind(parentItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return 0
        }
        else{
            return 1
        }
    }

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        fun bindSlider(data: ParentItem){
            var sliderView = itemView.findViewById<SliderView>(R.id.imageSlider)
            val adapter = SliderAdapter(data)
            sliderView.setSliderAdapter(adapter)
            sliderView.setIndicatorAnimation(IndicatorAnimationType.SWAP)
            sliderView.setSliderTransformAnimation(SliderAnimations.CUBEOUTROTATIONTRANSFORMATION);
            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            sliderView.setIndicatorSelectedColor(Color.WHITE);
            sliderView.setIndicatorUnselectedColor(Color.GRAY);
            sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
            sliderView.startAutoCycle();
        }

        fun bind(data:ParentItem){
            val title: TextView = itemView.findViewById(R.id.title)
            val childRecyclerView:RecyclerView = itemView.findViewById(R.id.child_recyclerview)
            title.text = data.parentItemTitle
            val childItemAdapter = ChildItemAdapter(data.childItem)
            childRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
            childRecyclerView.adapter = childItemAdapter
        }
    }
}