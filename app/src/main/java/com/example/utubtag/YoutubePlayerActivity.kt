package com.example.utubtag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

//https://www.youtube.com/watch?v=DatM4U30b0M&list=PL7z-ZXbhfoKE4BCAej1FMmpWNaFK_PxJ9
const val YOUTUBE_VIDEO_ID_KEY = "DatM4U30b0M&"
const val PLAYLIST_ID_KEY = "PL7z-ZXbhfoKE4BCAej1FMmpWNaFK_PxJ9"

class YoutubePlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    val TAG = "YoutubePlayerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube_player)
//        val layout = finViewById<ConstraintLayout>(R.id.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube_player, null) as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 100)
//        button1.text = "Button added"
//        layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        Log.d(TAG, "onInitializationSuccess")
        Toast.makeText(this, "Initialized Succesfully", Toast.LENGTH_LONG).show()
        if(!wasRestored){
            player?.cueVideo(YOUTUBE_VIDEO_ID_KEY)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, youTubeInitializationResult: YouTubeInitializationResult?) {
        Log.d(TAG, "onInitializationFailure")
        val REQUEST_CODE = 0
        if(youTubeInitializationResult?.isUserRecoverableError == true){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        }else{
            Toast.makeText(this, "Error starting player", Toast.LENGTH_LONG).show()
        }
    }
}