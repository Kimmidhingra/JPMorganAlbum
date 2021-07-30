package com.jpmorgan.album

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AlbumApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.LOGGIN_ENABLED) {
            Timber.plant(Timber.DebugTree())
        }
    }
}