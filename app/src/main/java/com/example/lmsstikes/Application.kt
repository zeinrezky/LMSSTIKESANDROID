package com.example.lmsstikes

import android.app.Application
import org.koin.core.context.startKoin
import android.os.StrictMode
import com.example.lmsstikes.di.*
import com.orhanobut.hawk.BuildConfig
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.EmptyLogger

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        Hawk.init(this).build()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            if (BuildConfig.DEBUG) androidLogger() else EmptyLogger()
            androidContext(this@Application)
            modules(
                listOf(
                    CoreModule,
                    HelperModule,
                    NetworkModule,
                    RepositoryModule,
                    ViewModelModule
                )
            )
        }
    }
}