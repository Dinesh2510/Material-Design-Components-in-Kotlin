package com.example.kotlindemoapp.Helper

import com.example.kotlindemoapp.Model.WallpaperItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.ArrayList

object WallpaperItemRepository {

    val wallpaperItemList: ArrayList<WallpaperItem>
        get() = Gson().fromJson<ArrayList<WallpaperItem>>(wallpaperItems, object : TypeToken<ArrayList<WallpaperItem>>() {

        }.type)

    internal var wallpaperItems = "[\n" +
            "  {\n" +
            "    \"name\":\"image1\",\n" +
            "    \"image\":\"gallery_bird_2\",\n" +
            "    \"likeCount\":\"2.3K\",\n" +
            "    \"viewCount\":\"4.2K\",\n" +
            "    \"user\" : {\n" +
            "      \"user_name\":\"Oliver\",\n" +
            "      \"ago\":\"2 hours ago\",\n" +
            "      \"user_image\":\"user_profile_man\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"image2\",\n" +
            "    \"image\":\"gallery_bird_3\",\n" +
            "    \"likeCount\":\"1.3K\",\n" +
            "    \"viewCount\":\"3.2K\",\n" +
            "    \"user\" : {\n" +
            "      \"user_name\":\"Harry\",\n" +
            "      \"ago\":\"3 hours ago\",\n" +
            "      \"user_image\":\"profile1\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"image3\",\n" +
            "    \"image\":\"gallery_bird_4\",\n" +
            "    \"likeCount\":\"1.1K\",\n" +
            "    \"viewCount\":\"3.8K\",\n" +
            "    \"user\" : {\n" +
            "      \"user_name\":\"Jacob\",\n" +
            "      \"ago\":\"3 hours ago\",\n" +
            "      \"user_image\":\"profile2\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"image4\",\n" +
            "    \"image\":\"gallery_bird_5\",\n" +
            "    \"likeCount\":\"2.2K\",\n" +
            "    \"viewCount\":\"3.6K\",\n" +
            "    \"user\" : {\n" +
            "      \"user_name\":\"Oliver\",\n" +
            "      \"ago\":\"5 hours ago\",\n" +
            "      \"user_image\":\"user_profile_man\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"image5\",\n" +
            "    \"image\":\"gallery_bird_8\",\n" +
            "    \"likeCount\":\"6.3K\",\n" +
            "    \"viewCount\":\"8.2K\",\n" +
            "    \"user\" : {\n" +
            "      \"user_name\":\"Harry\",\n" +
            "      \"ago\":\"5 hours ago\",\n" +
            "      \"user_image\":\"profile1\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"image6\",\n" +
            "    \"image\":\"gallery_bird_7\",\n" +
            "    \"likeCount\":\"1.3K\",\n" +
            "    \"viewCount\":\"6.2K\",\n" +
            "    \"user\" : {\n" +
            "      \"user_name\":\"Jacob\",\n" +
            "      \"ago\":\"6 hours ago\",\n" +
            "      \"user_image\":\"profile2\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"image7\",\n" +
            "    \"image\":\"gallery_bird_5\",\n" +
            "    \"likeCount\":\"10.3K\",\n" +
            "    \"viewCount\":\"13.2K\",\n" +
            "    \"user\" : {\n" +
            "      \"user_name\":\"Oliver\",\n" +
            "      \"ago\":\"6 hours ago\",\n" +
            "      \"user_image\":\"user_profile_man\"\n" +
            "    }\n" +
            "  }\n" +
            "\n" +
            "]"
}
