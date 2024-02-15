package ints.devotion.myapplication.model

import com.google.gson.Gson

abstract class JsonTransformable : JsonSerializable {
    inline fun <reified T : JsonSerializable> fromJson(json: String): T {
        return Gson().fromJson(json, T::class.java)
    }

}