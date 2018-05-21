package tech.ascendio.mvvm.data.vo

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class Gallery(
        @field:SerializedName("id") val id: String,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("description") val description: String,
        @field:SerializedName("datetime") val dateTime: Long,
        @field:SerializedName("account_url") val accountUrl: String?,
        @field:SerializedName("cover") val cover: String,
        @field:SerializedName("account_id") val accountId: Int?,
        @field:SerializedName("views") val views: Int,
        @field:SerializedName("link") val link: String,
        @field:SerializedName("ups") val ups: Int,
        @field:SerializedName("downs") val downs: Int?,
        @field:SerializedName("points") val points: Int?,
        @field:SerializedName("score") val score: Int?,
        @field:SerializedName("nsfw") val nsfw: Boolean,
        @field:SerializedName("section") val section: String,
        @field:SerializedName("in_gallery") val isGallery: Boolean,
        @field:SerializedName("images_count") val imagesCount: Boolean,
        @field:SerializedName("images") val images: List<Image>
)