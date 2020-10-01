package com.example.kotlindemoapp.Helper

import com.example.kotlindemoapp.Model.SectionGallery
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.ArrayList

object SectionGalleryRepository {

    internal var sectionGallery = "[\n" +
            "\n" +
            "  {\n" +
            "    \"id\":\"p2\",\n" +
            "    \"name\":\"City\",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"image_id\":\"i7\",\n" +
            "        \"image\":\"city_china\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i8\",\n" +
            "        \"image\":\"city_japan\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i9\",\n" +
            "        \"image\":\"city_korea\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i10\",\n" +
            "        \"image\":\"city_myanmar\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i11\",\n" +
            "        \"image\":\"city_singapore\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i12\",\n" +
            "        \"image\":\"city_thailand\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i13\",\n" +
            "        \"image\":\"city_thailand\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i19\",\n" +
            "        \"image\":\"city_thailand\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"p3\",\n" +
            "    \"name\":\"Cafe\",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"image_id\":\"i14\",\n" +
            "        \"image\":\"cafe1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i15\",\n" +
            "        \"image\":\"cafe2\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i16\",\n" +
            "        \"image\":\"cafe3\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i17\",\n" +
            "        \"image\":\"cafe1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i18\",\n" +
            "        \"image\":\"cafe4\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"p1\",\n" +
            "    \"name\":\"Birds\",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"image_id\":\"i1\",\n" +
            "        \"image\":\"gallery_bird_1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i2\",\n" +
            "        \"image\":\"gallery_bird_2\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i3\",\n" +
            "        \"image\":\"gallery_bird_3\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i4\",\n" +
            "        \"image\":\"gallery_bird_4\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i5\",\n" +
            "        \"image\":\"gallery_bird_5\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_id\":\"i6\",\n" +
            "        \"image\":\"gallery_bird_6\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "\n" +
            "]"

    fun getSectionGallery(): ArrayList<SectionGallery>? {
        return Gson().fromJson<ArrayList<SectionGallery>>(sectionGallery, object : TypeToken<ArrayList<SectionGallery>>() {

        }.type)
    }
}
