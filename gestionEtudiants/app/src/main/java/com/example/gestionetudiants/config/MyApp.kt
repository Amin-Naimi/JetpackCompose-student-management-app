package com.example.gestionetudiants.config

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}


/*
Cette classe permet d’avoir un accès global au contexte de l'application via MyApp.instance, par exemple :

val context = MyApp.instance.applicationContext

* */