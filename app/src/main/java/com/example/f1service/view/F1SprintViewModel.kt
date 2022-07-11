package com.example.f1service.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.model.DF1SprintDriverModels
import com.example.f1service.model.DF1SprintModels
import com.example.f1service.model.F1SprintModel.F1SprintModels
import com.google.gson.Gson
import com.google.gson.JsonObject

class F1SprintViewModel : ViewModel() {
    val sprintLiveData: MutableLiveData<DF1SprintModels> by lazy {
        MutableLiveData<DF1SprintModels> ()
    }

    private var gson = Gson()

    fun decodeResponse(jsonObject:JsonObject) {
        val mF1SprintModel = gson.fromJson(jsonObject, F1SprintModels::class.java)

        sprintLiveData.value = DF1SprintModels(
            circiutName = mF1SprintModel.mRData.raceTable.races[0].circuit.circuitName,
            circiutId = mF1SprintModel.mRData.raceTable.races[0].circuit.circuitId,
            sprintDriver = decodeDriverList(mF1SprintModel)

        )
    }

    private fun decodeDriverList(res : F1SprintModels):ArrayList<DF1SprintDriverModels> {
        var i = 0
        val driverList:ArrayList<DF1SprintDriverModels> = ArrayList()
        println("res:${res.mRData.raceTable}")
        while (i < res.mRData.raceTable.races[0].sprintResults.size) {
            val res = DF1SprintDriverModels(
                number = res.mRData.raceTable.races[0].sprintResults[i].number,
                position = res.mRData.raceTable.races[0].sprintResults[i].position,
                points = res.mRData.raceTable.races[0].sprintResults[i].points,
                driverId = res.mRData.raceTable.races[0].sprintResults[i].driver.driverId,
                name = res.mRData.raceTable.races[0].sprintResults[i].driver.givenName,
                familyName = res.mRData.raceTable.races[0].sprintResults[i].driver.familyName,
                team = res.mRData.raceTable.races[0].sprintResults[i].constructor.name

            )
            i++
            driverList.add(res)
        }
        return driverList
    }
}