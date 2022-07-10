package com.example.f1service.model.nextRace


import com.google.gson.annotations.SerializedName

data class RaceTable(
    @SerializedName("Races")
    val races: List<Race>,
    @SerializedName("round")
    val round: String,
    @SerializedName("season")
    val season: String
)