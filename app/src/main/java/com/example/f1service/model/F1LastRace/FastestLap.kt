package com.example.f1service.model.F1LastRace


import com.google.gson.annotations.SerializedName

data class FastestLap(
    @SerializedName("AverageSpeed")
    val averageSpeed: AverageSpeed,
    @SerializedName("lap")
    val lap: String,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("Time")
    val time: Time
)