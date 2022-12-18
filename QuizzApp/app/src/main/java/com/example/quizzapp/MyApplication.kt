package com.example.quizzapp

import android.app.Application
import com.example.quizzapp.model.Repo

class MyApplication : Application(){
   lateinit  var repo: Repo
    override fun onCreate() {
        super.onCreate()
        repo = Repo()
    }
}