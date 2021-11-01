package es.jfechevarria.local.di

import android.content.Context
import androidx.room.Room
import es.jfechevarria.domain.picture.dataSources.LocalPictureDataSource
import es.jfechevarria.local.AppDatabase
import es.jfechevarria.local.dao.PictureDao
import es.jfechevarria.local.dataSource.PictureDataSourceImpl
import es.jfechevarria.local.di.DatabaseModule.bindLocalPictureDataSource
import es.jfechevarria.local.di.DatabaseModule.provideAppDatabase
import es.jfechevarria.local.di.DatabaseModule.providePictureDao
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val databaseModule = module {
    single { provideAppDatabase(androidContext()) }
    single { providePictureDao(get()) }

    factory { bindLocalPictureDataSource(get()) }
}

internal object DatabaseModule {

    fun bindLocalPictureDataSource(pictureDao: PictureDao)
        = PictureDataSourceImpl(pictureDao) as LocalPictureDataSource


    fun providePictureDao(appDatabase: AppDatabase): PictureDao {
        return appDatabase.getPictureDao()
    }


    fun provideAppDatabase( appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "pictures_saved_db"
        ).build()
    }
}