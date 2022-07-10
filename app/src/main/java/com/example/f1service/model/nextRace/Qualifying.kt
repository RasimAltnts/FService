package com.example.f1service.model.nextRace


import com.google.gson.annotations.SerializedName

data class Qualifying(
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String
)