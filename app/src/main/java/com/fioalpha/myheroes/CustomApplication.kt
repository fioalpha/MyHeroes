package com.fioalpha.myheroes

import android.app.Application
import com.fioalpha.character.presentation.di.characterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CustomApplication)
            modules(
                characterModule
            )
        }
    }
}