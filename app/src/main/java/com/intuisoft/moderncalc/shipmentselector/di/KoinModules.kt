package com.intuisoft.moderncalc.shipmentselector.di

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.intuisoft.moderncalc.shipmentselector.data.DriverRepository
import com.intuisoft.moderncalc.shipmentselector.data.RegionManagerRepository
import com.intuisoft.moderncalc.shipmentselector.db.AppDatabase
import com.intuisoft.moderncalc.shipmentselector.ui.AppDateTimeFormatter
import com.intuisoft.moderncalc.shipmentselector.ui.viewmodel.DriverManagementPortalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        DriverManagementPortalViewModel(get(), get())
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "app-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    single {
        DriverRepository(get(), get())
    }

    single {
        RegionManagerRepository(get(), get())
    }
}


val timeModule = module {
    single {
        AppDateTimeFormatter()
    }

}

