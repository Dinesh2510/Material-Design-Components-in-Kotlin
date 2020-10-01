package com.example.kotlindemoapp.Model

import com.google.gson.annotations.SerializedName


class WallpaperItem(
    @field:SerializedName("name")
    var name: String, @field:SerializedName("image")
    var imageName: String, @field:SerializedName("likeCount")
    var likeCount: String, @field:SerializedName("viewCount")
    var viewCount: String, @field:SerializedName("user")
    var user: User
) {

    inner class User {
        @SerializedName("user_name")
        var nameName: String? = null

        @SerializedName("ago")
        var ago: String? = null

        @SerializedName("user_image")
        var user_image: String? = null
    }
}
