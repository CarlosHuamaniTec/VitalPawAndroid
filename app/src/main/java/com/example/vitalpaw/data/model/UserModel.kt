package com.example.vitalpaw.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: Long?,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("username") val username: String,
    @SerializedName("isConfirmed") val isConfirmed: Boolean
)

data class UserCreateModel(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("username") val username: String
)

data class PasswordChangeModel(
    @SerializedName("newPassword") val newPassword: String
)