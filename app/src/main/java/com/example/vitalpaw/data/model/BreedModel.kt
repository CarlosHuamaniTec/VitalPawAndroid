package com.example.vitalpaw.data.model

import com.google.gson.annotations.SerializedName

data class BreedModel(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: String,
    @SerializedName("maxTemperature") val maxTemperature: Float,
    @SerializedName("minTemperature") val minTemperature: Float,
    @SerializedName("maxHeartRate") val maxHeartRate: Int,
    @SerializedName("minHeartRate") val minHeartRate: Int
)