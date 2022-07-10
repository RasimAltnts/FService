package com.example.f1service.model.F1LastRace


import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("time")
    val time: String
)