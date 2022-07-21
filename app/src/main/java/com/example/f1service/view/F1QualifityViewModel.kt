package com.example.f1service.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.model.DF1Qualifying
import com.example.f1service.model.DQualityDriver
import com.example.f1service.model.F1QulifityRes.F1Qualifying
import com.example.f1service.model.F1QulifityRes.QualifyingResult
import com.google.gson.Gson
import com.google.gson.JsonObject

class F1QualifityViewModel : ViewModel() {


    val qualifity:MutableLiveData<DF1Qualifying> by lazy {
        MutableLiveData<DF1Qualifying> ()
    }

    private var gson = Gson()

    fun decodeResponse(jsonObject: JsonObject):DF1Qualifying {
        val mF1Qualifity = gson.fromJson(jsonObject, F1Qualifying::class.java)

        val result:DF1Qualifying = DF1Qualifying(
            circuitName = mF1Qualifity.mRData.raceTable.races[0].circuit.circuitName,
            circuitId = mF1Qualifity.mRData.raceTable.races[0].circuit.circuitId,
            driverInfo = driverInfo(mF1Qualifity.mRData.raceTable.races[0].qualifyingResults)
        )

        return result
    }

    private fun checkTime(time:String):String? {
        var qualiTime:String? = null
        if (time != null) {
            qualiTime = time
        }
        return qualiTime
    }

    private fun driverInfo(driverArray:List<QualifyingResult>):ArrayList<DQualityDriver> {
        val result:ArrayList<DQualityDriver> = ArrayList()
        var i = 0
        while (i < driverArray.size) {
            val res: DQualityDriver = DQualityDriver(
                position = driverArray[i].position,
                driverName = driverArray[i].driver.givenName,
                driverSurname = driverArray[i].driver.familyName,
                q1Time = checkTime(driverArray[i].q1),
                q2Time = checkTime(driverArray[i].q2),
                q3Time = checkTime(driverArray[i].q3),
                consId = driverArray[i].constructor.name
            )
            result.add(res)
            i++
        }
        return result
    }
}