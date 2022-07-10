package com.example.f1service.model.F1LastRace


import com.google.gson.annotations.SerializedName

data class TimeX(
    @SerializedName("millis")
    val millis: String,
    @SerializedName("time")
    val time: String
)