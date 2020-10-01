package com.example.kotlindemoapp.Model

import com.google.gson.annotations.SerializedName



class GeneralList(@field:SerializedName("name")
                  var name: String, @field:SerializedName("caption")
                  var caption: String, @field:SerializedName("image")
                  var image: String, @field:SerializedName("detail")
                  var detail: String) {

    var isOpened = false
}
