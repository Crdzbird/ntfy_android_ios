package ints.devotion.myapplication.model

import com.google.gson.annotations.SerializedName

data class IssNow(
    @SerializedName("iss_position")
    val issPosition: IssPosition = IssPosition(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("timestamp")
    val timestamp: Long = 0L
) : JsonTransformable()