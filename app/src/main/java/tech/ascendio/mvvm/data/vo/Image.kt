package tech.ascendio.mvvm.data.vo

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(primaryKeys = ["id"])
data class Image(
        @field:SerializedName("id") val id: String,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("description") val description: String?,
        @field:SerializedName("datetime") val dateTime: Long,
        @field:SerializedName("type") val type: String?,
        @field:SerializedName("url") val url: String?,
        @field:SerializedName("mp4") val mp4: String?,
        @field:SerializedName("gifv") val gifv: String?,
        @field:SerializedName("section") val section: String?,
        @field:SerializedName("animated") val animated: Boolean,
        @field:SerializedName("width") val width: Int,
        @field:SerializedName("height") val height: Int,
        @field:SerializedName("views") val views: Int,
        @field:SerializedName("nsfw") val nsfw: Boolean,
        @field:SerializedName("in_gallery") val inGallery: Boolean,
        @field:SerializedName("link") val link: String,
        @field:SerializedName("account_url") val accountUrl: String?,
        @field:SerializedName("account_id") val accountId: Int?,
        @field:SerializedName("ups") val ups: Int?,
        @field:SerializedName("downs") val downs: Int?,
        @field:SerializedName("points") val points: Int?,
        @field:SerializedName("score") val score: Int
)