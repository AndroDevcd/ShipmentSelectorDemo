package com.intuisoft.moderncalc.shipmentselector

import android.app.Application
import com.intuisoft.moderncalc.shipmentselector.di.databaseModule
import com.intuisoft.moderncalc.shipmentselector.di.repositoryModule
import com.intuisoft.moderncalc.shipmentselector.di.timeModule
import com.intuisoft.moderncalc.shipmentselector.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class ShipmentSelectorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ShipmentSelectorApplication)
            modules(listOf(databaseModule, timeModule, viewModelModule, repositoryModule))
        }
    }
}