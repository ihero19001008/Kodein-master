package com.example.kodein.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
 class Post
{
    @field:PrimaryKey
    @SerializedName("id")
    var id: Int= 0
    @SerializedName("url")
    var url: String=""
    @SerializedName("large_url")
    var large_url: String=""
        get() = this.url

    @SerializedName("source_id")
    var source_id: String=""
        get() ="1"
    @SerializedName("site")
     var site: String=""
        get() = "1"
    @SerializedName("copyright")
    var copyright: String =""
        get() = "1"


}

