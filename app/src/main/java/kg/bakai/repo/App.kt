package kg.bakai.repo

import android.app.Application
import kg.bakai.repo.di.networkModule
import kg.bakai.repo.di.repoModule
import kg.bakai.repo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repoModule, viewModelModule))
        }
    }
}