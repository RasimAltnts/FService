package com.example.f1service.model

import com.example.f1service.model.nextRace.FirstPractice
import com.example.f1service.model.nextRace.Qualifying
import com.example.f1service.model.nextRace.SecondPractice
import com.example.f1service.model.nextRace.ThirdPractice

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
)
