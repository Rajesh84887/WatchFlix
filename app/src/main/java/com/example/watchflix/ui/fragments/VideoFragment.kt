package com.example.watchflix.ui.fragments

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.example.watchflix.R

class VideoFragment : Fragment() {
//    private var swappingOrientation = false
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if(savedInstanceState == null) {
//            swappingOrientation = true
//            activity?.apply {
//                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if(!swappingOrientation) {
//            activity?.apply {
//                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//            }
//        }
//        swappingOrientation = false
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val simpleVideoView = view.findViewById<VideoView>(R.id.simpleVideoView)
        var url = "https://zee-demo.s3.ap-south-1.amazonaws.com/Mission_+Impossible+%E2%80%93+Dead+Reckoning+Part+One+_+Official+Trailer+(2023+Movie)+-+Tom+Cruise.mp4"

        var uri : Uri = Uri.parse(url)

        simpleVideoView.setVideoURI(uri)

        val mediaController = MediaController(context)

        mediaController.setAnchorView(simpleVideoView)
        mediaController.setMediaPlayer(simpleVideoView)
        simpleVideoView.setMediaController(mediaController)
        simpleVideoView.start()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("check","Back Button Clicked")
                Navigation.findNavController(view).navigateUp()
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(viewLifecycleOwner, onBackPressedCallback)
    }
}