package com.example.watchflix

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d("check","Second Activity")

        val simpleVideoView = findViewById<VideoView>(R.id.simpleVideoView)
        var url = "https://zee-demo.s3.ap-south-1.amazonaws.com/Mission_+Impossible+%E2%80%93+Dead+Reckoning+Part+One+_+Official+Trailer+(2023+Movie)+-+Tom+Cruise.mp4"

        var uri : Uri = Uri.parse(url)

        simpleVideoView.setVideoURI(uri)

        val mediaController = MediaController(this)

        mediaController.setAnchorView(simpleVideoView)
        mediaController.setMediaPlayer(simpleVideoView)
        simpleVideoView.setMediaController(mediaController)
        simpleVideoView.start()
    }

    override fun onBackPressed() {
        Log.d("click","Back click")
        finish()
    }
}