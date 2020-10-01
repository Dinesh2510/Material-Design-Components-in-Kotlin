package com.example.kotlindemoapp.Helper


import com.example.kotlindemoapp.Model.Chat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.ArrayList


object GeneralChatListRepository {
    val generalChatList: ArrayList<Chat>
        get() = Gson().fromJson<ArrayList<Chat>>(json, object : TypeToken<ArrayList<Chat>>() {

        }.type)

    internal var json = "[\n" +
            "  {\n" +
            "    \"id\":\"John\",\n" +
            "    \"name\":\"John\",\n" +
            "    \"text\":\"Hey I have something to talk about the things we discussed yesterday, call me when you are free\",\n" +
            "    \"image\":\"profile1\",\n" +
            "    \"count\":\"10\",\n" +
            "    \"time\":\"9:56 am\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"Clara\",\n" +
            "    \"name\":\"Clara\",\n" +
            "    \"text\":\"Let's go shopping, call me when you are ready\",\n" +
            "    \"image\":\"profile2\",\n" +
            "    \"count\":\"1\",\n" +
            "    \"time\":\"9:56 am\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"Clara\",\n" +
            "    \"name\":\"Harry\",\n" +
            "    \"text\":\"Ron told me you lost my wand, Hermonie. You have to buy a new one for me, the most expensive one\",\n" +
            "    \"image\":\"profile1\",\n" +
            "    \"count\":\"0\",\n" +
            "    \"time\":\"9:56 am\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"Hermonie\",\n" +
            "    \"name\":\"Hermonie\",\n" +
            "    \"text\":\"Don't believe everything that blonde says. He always talk about nonsense things. I already have sent your wand with my owl. It is on the way. Should be right there in any minute\",\n" +
            "    \"image\":\"profile1\",\n" +
            "    \"count\":\"2\",\n" +
            "    \"time\":\"9:56 am\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"Ron\",\n" +
            "    \"name\":\"Ron\",\n" +
            "    \"text\":\"Haha, Harry . Gotcha. Your face was so funny when I told you your wand was lost\",\n" +
            "    \"image\":\"profile2\",\n" +
            "    \"count\":\"0\",\n" +
            "    \"time\":\"9:56 am\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"Dumbledore\",\n" +
            "    \"name\":\"Dumbledore\",\n" +
            "    \"text\":\"Kids you all will be kicked out of school if you keep sneaking out when everyone is sleeping. Don't ruin peoples' peaceful dreams.\",\n" +
            "    \"image\":\"profile2\",\n" +
            "    \"count\":\"0\",\n" +
            "    \"time\":\"9:56 am\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"Voldemort\",\n" +
            "    \"name\":\"Voldemort\",\n" +
            "    \"text\":\"Kids, you all are welcomed to my property if Dumbledore kicked you out. I need more people to cheer me up before I do my nose surgery. P.S : Don't forget to bring all the gold coins you have. I need those to pay the doctor\",\n" +
            "    \"image\":\"profile2\",\n" +
            "    \"count\":\"1\",\n" +
            "    \"time\":\"9:56 am\"\n" +
            "  }\n" +
            "]"
}
