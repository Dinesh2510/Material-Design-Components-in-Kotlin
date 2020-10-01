package com.example.kotlindemoapp.Helper

import com.example.kotlindemoapp.Model.MediaMusic
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object MediaMp3Repository {
    val categoryList: List<MediaMusic>
        get() = Gson().fromJson<List<MediaMusic>>(json, object : TypeToken<List<MediaMusic>>() {

        }.type)

    private val json = "[\n" +
            "  {\n" +
            "    \"title\":\"Sweet Child \",\n" +
            "    \"image\":\"cafe\",\n" +
            "    \"singer\":\"Guns N'Roses\",\n" +
            "    \"song\":\"bensound_happyrock\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Back in Black\",\n" +
            "    \"image\":\"cafe1\",\n" +
            "    \"singer\":\"AC/DC\",\n" +
            "    \"song\":\"bensound_highoctane\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"November Rain\",\n" +
            "    \"image\":\"cafe2\",\n" +
            "    \"singer\":\"Guns N'Roses\",\n" +
            "    \"song\":\"bensound_rumble\"\n" +
            "  }\n" +
            "]"
}
