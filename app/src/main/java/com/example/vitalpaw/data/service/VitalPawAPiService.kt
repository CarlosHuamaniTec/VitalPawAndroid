package com.example.vitalpaw.data.service

import com.example.vitalpaw.data.model.*
import retrofit2.http.*

interface VitalPawApiService {
    // Pets
    @POST("pets")
    suspend fun createPet(@Body pet: PetCreateModel): PetModel

    @GET("pets/{id}")
    suspend fun getPet(@Path("id") id: Long): PetModel

    @PUT("pets/{id}")
    suspend fun updatePet(@Path("id") id: Long, @Body pet: PetCreateModel): PetModel

    // Users
    @POST("users")
    suspend fun createUser(@Body user: UserCreateModel): UserModel

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Long): UserModel

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: UserCreateModel): UserModel

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Long)

    @GET("users/confirm/{token}")
    suspend fun confirmAccount(@Path("token") token: String): UserModel

    @POST("users/{id}/change-password")
    suspend fun changePassword(@Path("id") id: Long, @Body password: PasswordChangeModel): UserModel

    // Alerts
    @GET("alerts/pet/{petId}")
    suspend fun getAlertsByPetId(
        @Path("petId") petId: Long,
        @Query("limit") limit: Int = 10
    ): List<AlertModel>

    @DELETE("alerts/{alertId}")
    suspend fun deleteAlert(@Path("alertId") alertId: Long)

    // Breeds
    @POST("breeds")
    suspend fun createBreed(@Body breed: BreedModel): BreedModel

    @GET("breeds/{id}")
    suspend fun getBreed(@Path("id") id: Long): BreedModel

    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedModel>

    // Devices
    @POST("devices")
    suspend fun createDevice(@Body device: DeviceModel): DeviceModel

    @GET("devices/{id}")
    suspend fun getDevice(@Path("id") id: Long): DeviceModel

    @GET("devices/device/{deviceId}")
    suspend fun getDeviceByDeviceId(@Path("deviceId") deviceId: String): DeviceModel

    @PUT("devices/{id}")
    suspend fun updateDevice(@Path("id") id: Long, @Body device: DeviceModel): DeviceModel

    @DELETE("devices/{id}")
    suspend fun deleteDevice(@Path("id") id: Long)
}