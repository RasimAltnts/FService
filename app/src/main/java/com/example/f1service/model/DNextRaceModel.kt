package com.example.f1service.model

import com.example.f1service.model.F1NextRace.FirstPractice
import com.example.f1service.model.F1NextRace.Qualifying
import com.example.f1service.model.F1NextRace.SecondPractice

data class DNextRaceModel(
    val nextRaceName:String,
    val nextRaceCircuitName:String,
    val nextRaceDate:String,
    val nextRaceTime:String,
    val nextRaceCountry:String,
    val nextRaceCity:String,
    val nextRaceFirstPractice: FirstPractice,
    val nextRaceSecondPractice: SecondPractice,
    val qualifying: Qualifying,
    val nextRaceRound:String,
    val nextRaceYear:String,
    val sprintTime:String?,
    val sprintDate:String?,
)
