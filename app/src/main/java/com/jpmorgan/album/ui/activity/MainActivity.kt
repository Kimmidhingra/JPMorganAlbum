package com.jpmorgan.album.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jpmorgan.album.BuildConfig
import com.jpmorgan.album.R
import com.jpmorgan.album.ui.fragments.AlbumFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction  = supportFragmentManager.beginTransaction()
        transaction.add(R.id.flAlbum,AlbumFragment())
        transaction.commit()
    }
}