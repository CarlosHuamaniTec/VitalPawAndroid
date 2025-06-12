package com.example.vitalpaw.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class AlertModel(
    @SerializedName("id") val id: Long?,
    @SerializedName("petId") val petId: Long,
    @SerializedName("message") val message: String,
    @SerializedName("timestamp") val timestamp: LocalDateTime?,
    @SerializedName("type") val type: String,
    @SerializedName("severity") val severity: String,
    @SerializedName("pulse") val pulse: Int?,
    @SerializedName("temperature") val temperature: Float?
)