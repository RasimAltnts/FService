package com.example.f1service.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.model.DLastRace
import com.example.f1service.model.DLastRaceResult
import com.example.f1service.model.F1LastRace.F1LastRace
import com.example.f1service.model.F1LastRace.Race
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.lang.Exception

class HomepageViewModel : ViewModel() {

    private var gson = Gson()

    val lastRaceInfo: MutableLiveData<DLastRace> by lazy {
        MutableLiveData<DLastRace> ()
    }


    fun decodeLastRaceResponse(jsonObject: JsonObject) {
        val mDLastRaceResult = gson.fromJson(jsonObject, F1LastRace::class.java)


        val lastRace = DLastRace(
            circuitName = mDLastRaceResult.mRData.raceTable.races[0].circuit.circuitName,
            circuitId = mDLastRaceResult.mRData.raceTable.races[0].circuit.circuitId,
            pilot = decodeLastRaceResultResponse(mDLastRaceResult.mRData.raceTable.races[0])
        )

        lastRace.let {
            lastRaceInfo.value = lastRace
        }
    }

    private fun decodeLastRaceResultResponse(value: Race):ArrayList<DLastRaceResult> {
        var i = 0
        val result:ArrayList<DLastRaceResult> = ArrayList()

        while (i < value.results.size) {
            val res = DLastRaceResult(
                position = value.results[i].position,
                carNumber = value.results[i].number,
                point = value.results[i].points,
                pilotName = value.results[i].driver.givenName,
                pilotSurname = value.results[i].driver.familyName,
                constructorName = value.results[i].constructor.name,
                constructorId = value.results[i].constructor.constructorId,
                driverId = value.results[i].driver.driverId,
                fastestLap = "-1"
            )
            i += 1
            result.add(res)
        }
        return result
    }

    private fun checkFastestRankOrNull(rank: String?):String? {
        var value:String? = null
        try {
            if (rank != null) {
                value = rank
            }
            else {
                value = "-1"
            }
        }catch (e: Exception) {
            value = "-1"
        }
        return value
    }
}