package com.example.kotlindemoapp.Model

import com.google.gson.annotations.SerializedName

class Country {

    @SerializedName("name")
    var name: String

    @SerializedName("desc")
    lateinit var desc: String

    @SerializedName("image")
    lateinit var imageName: String

    @SerializedName("flag")
    lateinit var flagName: String

    var isGroupHeader = false

    constructor(name: String) {
        this.name = name
        this.isGroupHeader = true
    }

    constructor(name: String, desc: String, imageName: String, flagName: String) {
        this.name = name
        this.desc = desc
        this.imageName = imageName
        this.flagName = flagName
    }
}