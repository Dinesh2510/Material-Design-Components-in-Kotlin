package com.example.kotlindemoapp.Model

import com.google.gson.annotations.SerializedName

class SectionGallery(@field:SerializedName("id")
                     var id: String, @field:SerializedName("name")
                     var name: String, @field:SerializedName("images")
                     var imageList: List<Image>) {

    inner class Image {

        @SerializedName("image_id")
        lateinit var imageId: String

        @SerializedName("image")
        lateinit var image: String
    }


}