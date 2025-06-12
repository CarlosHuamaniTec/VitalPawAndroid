package com.example.vitalpaw.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class PetModel(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: String,
    @SerializedName("breedId") val breedId: Long?,
    @SerializedName("birthDate") val birthDate: LocalDate?,
    @SerializedName("ownerId") val ownerId: Long,
    @SerializedName("photo") val photo: String?
)

data class PetCreateModel(
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: String,
    @SerializedName("breedId") val breedId: Long?,
    @SerializedName("birthDate") val birthDate: LocalDate?,
    @SerializedName("ownerId") val ownerId: Long,
    @SerializedName("photo") val photo: String?
)