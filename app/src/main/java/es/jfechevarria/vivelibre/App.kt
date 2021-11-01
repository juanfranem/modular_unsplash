package es.jfechevarria.vivelibre

import android.app.Application
import es.jfechevarria.app_base.di.repositoryModule
import es.jfechevarria.app_base.di.useCaseModule
import es.jfechevarria.local.di.databaseModule
import es.jfechevarria.remote.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(
                remoteModule,
                databaseModule,
                repositoryModule,
                useCaseModule
            ))
        }
    }
}