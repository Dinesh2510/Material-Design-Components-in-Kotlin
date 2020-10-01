package com.example.kotlindemoapp.Model

import com.google.gson.annotations.SerializedName

class MediaMusic(
    @field:SerializedName("title")
    var title: String, @field:SerializedName("image")
    var image: String, @field:SerializedName("singer")
    var singer: String, @field:SerializedName("song")
    var song: String
)


