package com.example.f1service.logic

import com.example.f1service.model.DNextRaceModel
import com.example.f1service.model.nextRace.NextRaceModels
import com.google.gson.Gson
import com.google.gson.JsonObject

class MainActivityLogic {

    private var gson = Gson()

    fun decodeNextRaceResponse(jsonObject: JsonObject): DNextRaceModel {
        val mDF1NextRace = gson.fromJson(jsonObject, NextRaceModels::class.java)
        return DNextRaceModel(
            nextRaceName = mDF1NextRace.mRData.raceTable.races[0].raceName,
            nextRaceCircuitName = mDF1NextRace.mRData.raceTable.races[0].circuit.circuitName,
            nextRaceDate = mDF1NextRace.mRData.raceTable.races[0].date,
            nextRaceTime = mDF1NextRace.mRData.raceTable.races[0].time,
            nextRaceCountry = mDF1NextRace.mRData.raceTable.races[0].circuit.location.country,
            nextRaceCity = mDF1NextRace.mRData.raceTable.races[0].circuit.location.locality,
            nextRaceFirstPractice = mDF1NextRace.mRData.raceTable.races[0].firstPractice,
            nextRaceSecondPractice = mDF1NextRace.mRData.raceTable.races[0].secondPractice,
            qualifying = mDF1NextRace.mRData.raceTable.races[0].qualifying,
            nextRaceRound = mDF1NextRace.mRData.raceTable.races[0].round,
            nextRaceYear = mDF1NextRace.mRData.raceTable.races[0].season
        )
    }
}