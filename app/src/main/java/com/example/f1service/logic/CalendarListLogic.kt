package com.example.f1service.logic

import com.example.f1service.model.DF1CurrentSession
import com.example.f1service.model.F1CurrentSession
import com.example.f1service.model.F1CurrentSessionModel.DF1CurrentSessionModels
import com.example.f1service.model.F1CurrentSessionModel.Race
import com.google.gson.Gson
import com.google.gson.JsonObject

class CalendarListLogic {

    private var gson = Gson()
    fun decodeResponse(jsonObject: JsonObject):DF1CurrentSession {
        var currentSessionModels = gson.fromJson(jsonObject, DF1CurrentSessionModels::class.java)
        val result:DF1CurrentSession = DF1CurrentSession(
            circuitName = currentSessionModels.mRData.raceTable.races[0].circuit.circuitName,
            circuitId = currentSessionModels.mRData.raceTable.races[0].circuit.circuitId,
            location = currentSessionModels.mRData.raceTable.races[0].circuit.location,
            session = sessionList(currentSessionModels.mRData.raceTable.races)
        )

        return result
    }


    private fun sessionList(list:List<Race>):ArrayList<F1CurrentSession> {
        val result:ArrayList<F1CurrentSession> = ArrayList()
        var i = 0

        while(i < list.size){
            val res = F1CurrentSession(
                date = list[i].date,
                time = list[i].time,
                session = list[i].season,
                round = list[i].round,
                racename = list[i].raceName
            )
            result.add(res)
            i++
        }

        return result
    }
}