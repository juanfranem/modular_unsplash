package es.jfechevarria.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.jfechevarria.local.dao.PictureDao
import es.jfechevarria.local.domain.PictureDB

@Database(entities = [PictureDB::class], version = 1)
internal abstract class AppDatabase: RoomDatabase() {
    abstract fun getPictureDao(): PictureDao
}