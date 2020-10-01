package com.example.kotlindemoapp.Model

import com.google.gson.annotations.SerializedName


class Course(@field:SerializedName("title")
             var title: String, @field:SerializedName("desc")
             var desc: String, @field:SerializedName("category")
             var category: String, @field:SerializedName("length")
             var length: String, @field:SerializedName("image")
             var image: String, @field:SerializedName("viewCount")
             var viewCount: String, @field:SerializedName("status")
             var status: String, @field:SerializedName("percent")
             var percent: String)
