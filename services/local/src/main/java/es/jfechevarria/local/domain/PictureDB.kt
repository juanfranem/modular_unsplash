package es.jfechevarria.local.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.valueObjects.*

@Entity(tableName = "pictures")
internal data class PictureDB(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: String?,
    val imageUrl: String,
    val likes: Int,
    val creatorUserName: String,
    val insertedOn: Long = System.currentTimeMillis()
) {
    fun toDomain(): Picture {
        return Picture(
            IdPictureVO(id),
            if (description == null) null else DescriptionPictureVO(description),
            ImageURIPictureVO(imageUrl),
            LikesPictureVO(likes),
            CreatorUserNamePictureVO(creatorUserName),
            isSaved = IsSavedPictureVO(true)
        )
    }

    companion object {
        fun Picture.toDB(): PictureDB {
            return PictureDB(
                id.value,
                description?.value,
                imageUrl.value,
                likesCount.value,
                creatorUserNamePictureVO.value
            )
        }
    }
}