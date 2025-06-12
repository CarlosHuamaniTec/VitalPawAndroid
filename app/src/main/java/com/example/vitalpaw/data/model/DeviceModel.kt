package com.example.vitalpaw.data.model

import com.google.gson.annotations.SerializedName

data class DeviceModel(
    @SerializedName("id") val id: Long?,
    @SerializedName("petId") val petId: Long,
    @SerializedName("deviceId") val deviceId: String,
    @SerializedName("isActive") val isActive: Boolean?
)