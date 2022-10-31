package com.kartik.grevocab

import androidx.multidex.MultiDexApplication
import com.kartik.grevocab.base.AndroidResProvider
import com.kartik.grevocab.base.ResProvider
import com.kartik.grevocab.utility.DataHolder
import com.kartik.grevocab.utility.TimeUtils
import com.kartik.grevocab.vm.FragmentReservationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@OpenForTesting
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val appModule = module {

    viewModel { FragmentReservationViewModel(get(), DataHolder) }

    single { TimeUtils() }
    single { AndroidResProvider(get()) as ResProvider }
}
