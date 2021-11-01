package es.jfechevarria.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.jfechevarria.local.domain.PictureDB

@Dao
internal interface PictureDao {
    @Query("SELECT * FROM pictures ORDER BY insertedOn DESC LIMIT :total OFFSET :offset")
    fun get(offset: Int, total: Int): List<PictureDB>

    @Query("SELECT COUNT(id) FROM pictures WHERE id = :id")
    suspend fun exists(id: String): Int

    @Query("SELECT COUNT(id) FROM pictures LIMIT 1")
    suspend fun count(): Int

    @Insert
    suspend fun insert(vararg pictureDB: PictureDB)

    @Query("DELETE FROM pictures WHERE id = :id")
    suspend fun delete(id: String)
}