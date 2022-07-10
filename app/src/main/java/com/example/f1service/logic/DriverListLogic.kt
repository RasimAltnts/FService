package com.example.f1service.logic

import com.example.f1service.model.DF1DriversModels
import com.example.f1service.model.F1Driver.Driver
import com.example.f1service.model.F1Driver.F1DriverStanding
import com.google.gson.Gson
import com.google.gson.JsonObject

class DriverListLogic {
    private var gson = Gson()

    fun decodeResponse(jsonObject: JsonObject):ArrayList<DF1DriversModels> {
        val mF1DriverModels = gson.fromJson(jsonObject,F1DriverStanding::class.java)
        val result:ArrayList<DF1DriversModels> = ArrayList()
        var i = 0

        while (i < mF1DriverModels.mRData.standingsTable.standingsLists[0].driverStandings.size) {
            val res:DF1DriversModels = DF1DriversModels(
                pilotName = mF1DriverModels.mRData.standingsTable.standingsLists[0].driverStandings[i].driver.givenName,
                pilotSurname = mF1DriverModels.mRData.standingsTable.standingsLists[0].driverStandings[i].driver.familyName,
                constructorId = mF1DriverModels.mRData.standingsTable.standingsLists[0].driverStandings[i].constructors[0].constructorId,
                points = mF1DriverModels.mRData.standingsTable.standingsLists[0].driverStandings[i].points,
                position = mF1DriverModels.mRData.standingsTable.standingsLists[0].driverStandings[i].position
            )
            result.add(res)
            i++
        }
        return result
    }
}