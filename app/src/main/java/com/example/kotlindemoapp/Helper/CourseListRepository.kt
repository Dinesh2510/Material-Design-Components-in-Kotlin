package com.example.kotlindemoapp.Helper

import com.example.kotlindemoapp.Model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.Collections


object CourseListRepository {

    val courseList: List<Course>
        get() {
            val courseList =
                Gson().fromJson<List<Course>>(courseJson, object : TypeToken<List<Course>>() {

                }.type)

            Collections.shuffle(courseList)
            return courseList
        }

    internal var courseJson = "[\n" +
            "  {\n" +
            "    \"title\":\"Early math\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Math\",\n" +
            "    \"length\":\"1h 30mins\",\n" +
            "    \"image\":\"education_img_1\",\n" +
            "    \"viewCount\":\"20,000\",\n" +
            "    \"status\":\"Started\",\n" +
            "    \"percent\":\"10\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Arithmetic\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Math\",\n" +
            "    \"length\":\"2h 10mins\",\n" +
            "    \"image\":\"education_img_2\",\n" +
            "    \"viewCount\":\"21,000\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"percent\":\"0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Algebra Basic\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Math\",\n" +
            "    \"length\":\"3h 30mins\",\n" +
            "    \"image\":\"education_img_3\",\n" +
            "    \"viewCount\":\"30,000\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"percent\":\"0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Geometry\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Math\",\n" +
            "    \"length\":\"3h 20mins\",\n" +
            "    \"image\":\"education_img_4\",\n" +
            "    \"viewCount\":\"23,000\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"percent\":\"0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Trigonometry\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Math\",\n" +
            "    \"length\":\"1h 20mins\",\n" +
            "    \"image\":\"education_img_5\",\n" +
            "    \"viewCount\":\"13,000\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"percent\":\"0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Biology\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Science\",\n" +
            "    \"length\":\"1h 20mins\",\n" +
            "    \"image\":\"education_img_6\",\n" +
            "    \"viewCount\":\"15,000\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"percent\":\"0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Chemistry\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Science\",\n" +
            "    \"length\":\"3h 20mins\",\n" +
            "    \"image\":\"education_img_7\",\n" +
            "    \"viewCount\":\"35,000\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"percent\":\"0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Physics\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Science\",\n" +
            "    \"length\":\"2h 20mins\",\n" +
            "    \"image\":\"education_img_8\",\n" +
            "    \"viewCount\":\"25,200\",\n" +
            "    \"status\":\"Started\",\n" +
            "    \"percent\":\"20\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Organic Chemistry\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Science\",\n" +
            "    \"length\":\"3h 20mins\",\n" +
            "    \"image\":\"education_img_9\",\n" +
            "    \"viewCount\":\"35,000\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"percent\":\"0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Microeconomics\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Economic\",\n" +
            "    \"length\":\"3h 20mins\",\n" +
            "    \"image\":\"education_img_10\",\n" +
            "    \"viewCount\":\"55,000\",\n" +
            "    \"status\":\"Started\",\n" +
            "    \"percent\":\"40\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Macroeconomics and finance\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Economic\",\n" +
            "    \"length\":\"4h 20mins\",\n" +
            "    \"image\":\"education_img_11\",\n" +
            "    \"viewCount\":\"56,000\",\n" +
            "    \"status\":\"Started\",\n" +
            "    \"percent\":\"20\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Finance & capital markets\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Economic\",\n" +
            "    \"length\":\"2h 20mins\",\n" +
            "    \"image\":\"education_img_12\",\n" +
            "    \"viewCount\":\"15,000\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"percent\":\"0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Entrepreneurship\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ut lectus eros. Quisque porta pretium commodo.\",\n" +
            "    \"category\":\"Economic\",\n" +
            "    \"length\":\"5h 20mins\",\n" +
            "    \"image\":\"education_img_14\",\n" +
            "    \"viewCount\":\"75,000\",\n" +
            "    \"status\":\"Started\",\n" +
            "    \"percent\":\"20\"\n" +
            "  }\n" +
            "\n" +
            "]"

}
