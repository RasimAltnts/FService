package com.example.f1service.model.F1LastRace


import com.google.gson.annotations.SerializedName

data class Race(
    @SerializedName("Circuit")
    val circuit: Circuit,
    @SerializedName("date")
    val date: String,
    @SerializedName("raceName")
    val raceName: String,
    @SerializedName("Results")
    val results: List<Result>,
    @SerializedName("round")
    val round: String,
    @SerializedName("season")
    val season: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("url")
    val url: String
)