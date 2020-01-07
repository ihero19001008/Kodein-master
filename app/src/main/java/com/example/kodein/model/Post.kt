package com.example.kodein.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Post(
    @field:PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("url")
    var url: String,
    @SerializedName("large_url")
    var large_url: String,
    @SerializedName("source_id")
    var source_id: Any,
    @SerializedName("site")
    var site:String,
    @SerializedName("copyright")
    var copyright: String
)

