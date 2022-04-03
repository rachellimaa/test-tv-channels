package com.rachellimaa.channel

import android.app.Application
import com.rachellimaa.config.network.NetworkModule
import com.rachellimaa.features.channel.di.ChannelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
        }

        loadKoinModules(
            listOf(
                NetworkModule().provideModule(),
                ChannelModule().provideModule(),
            )
        )
    }
}