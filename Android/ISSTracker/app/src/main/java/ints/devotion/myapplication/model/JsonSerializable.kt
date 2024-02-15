package ints.devotion.myapplication.model

import com.google.gson.Gson

interface JsonSerializable {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        inline fun <reified T : JsonSerializable> fromJson(json: String): T {
            return Gson().fromJson(json, T::class.java)
        }
    }


}