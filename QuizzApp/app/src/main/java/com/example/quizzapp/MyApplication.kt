package com.example.quizzapp

import android.app.Application
import com.example.quizzapp.model.Repo
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}