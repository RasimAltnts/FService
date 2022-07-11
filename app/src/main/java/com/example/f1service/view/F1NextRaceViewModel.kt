package com.example.f1service.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.model.DNextRaceModel
import com.example.f1service.model.F1NextRace.NextRaceModels
import com.google.gson.Gson
import com.google.gson.JsonObject

class F1NextRaceViewModel : ViewModel() {
    val nextRaceInfo: MutableLiveData<DNextRaceModel> by lazy {
        MutableLiveData<DNextRaceModel> ()
    }

    private var gson = Gson()

    fun decodeNextRaceResponse(jsonObject: JsonObject) {
        val mDF1NextRace = gson.fromJson(jsonObject, NextRaceModels::class.java)

        if (mDF1NextRace.mRData.raceTable.races[0].sprint != null) {
            nextRaceInfo.value =  DNextRaceModel(
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
                nextRaceYear = mDF1NextRace.mRData.raceTable.races[0].season,
                sprintTime = mDF1NextRace.mRData.raceTable.races[0].sprint.time,
                sprintDate = mDF1NextRace.mRData.raceTable.races[0].sprint.date
            )
        }
        else {
            nextRaceInfo.value =  DNextRaceModel(
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
                nextRaceYear = mDF1NextRace.mRData.raceTable.races[0].season,
                sprintTime = null,
                sprintDate = null,
            )
        }
    }
}