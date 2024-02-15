package ints.devotion.myapplication.model

import com.google.gson.annotations.SerializedName

data class IssPosition(
    @SerializedName("latitude")
    val latitude: String = "0",
    @SerializedName("longitude")
    val longitude: String = "0"
) : JsonTransformable()