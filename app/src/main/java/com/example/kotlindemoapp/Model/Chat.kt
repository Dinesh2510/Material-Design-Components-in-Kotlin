package com.example.kotlindemoapp.Model

import com.google.gson.annotations.SerializedName

class Chat(
    @field:SerializedName("id")
    var ID: String, @field:SerializedName("name")
    var Name: String, @field:SerializedName("text")
    var Message: String, @field:SerializedName("image")
    var Image: String, @field:SerializedName("count")
    var Count: String, @field:SerializedName("time")
    var Time: String
)

