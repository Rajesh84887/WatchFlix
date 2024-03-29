package com.example.watchflix

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.watchflix.databinding.ActivityMainBinding
import com.example.watchflix.ui.adapters.ViewPagerAdapter
import com.example.watchflix.ui.fragments.Categories
import com.example.watchflix.ui.fragments.Documentaries
import com.example.watchflix.ui.fragments.Movies
import com.example.watchflix.ui.fragments.TvSeries
import com.example.watchflix.viewmodel.MoviesListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.appName.text= Html.fromHtml("<font color=${Color.WHITE}>Watch</font>" + "<font color=${Color.RED}>Flix</font>")

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addItem(Movies(),"Movies")
        adapter.addItem(TvSeries(),"TV Series")
        adapter.addItem(Documentaries(),"Documentaries")
        adapter.addItem(Categories(),"Categories")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

//        val paint = binding.title.paint
//        val width = paint.measureText(binding.title.text.toString())
//        val textShader: Shader = LinearGradient(0f, 0f, width, binding.title.textSize, intArrayOf(
//            Color.parseColor("#F97C3C"),
//            Color.parseColor("#FDB54E"),
//            Color.parseColor("#8446CC")
//        ), null, Shader.TileMode.REPEAT)
//        binding.title.paint.setShader(textShader)

    }
}