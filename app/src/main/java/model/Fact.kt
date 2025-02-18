package model

import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("_id")
    val id: String,
    @SerializedName("__v")
    val version: Int,
    val text: String,
    val updatedAt: String,
    val deleted: Boolean,
    val source: String,
    val sentCount: Int
)