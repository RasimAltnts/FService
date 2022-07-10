package com.example.f1service.model.F1LastRace


import com.google.gson.annotations.SerializedName

data class AverageSpeed(
    @SerializedName("speed")
    val speed: String,
    @SerializedName("units")
    val units: String
)