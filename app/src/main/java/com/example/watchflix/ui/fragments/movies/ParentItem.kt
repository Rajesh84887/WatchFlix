package com.example.watchflix.ui.fragments.movies

import com.example.watchflix.network.data.Result

data class ParentItem(val parentItemTitle: String, val childItem: List<ChildItem>) {
}
data class ParentItemTop(val imageId:Int,val title:String,val content:String,val rating:Float){
}