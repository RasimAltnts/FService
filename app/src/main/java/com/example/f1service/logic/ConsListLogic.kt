package com.example.f1service.logic

import com.example.f1service.model.DF1ConstructorModel
import com.example.f1service.model.F1Const.F1Constructor
import com.google.gson.Gson
import com.google.gson.JsonObject

class ConsListLogic {
    private var gson = Gson()

    fun decodeResponse(jsonObject: JsonObject):ArrayList<DF1ConstructorModel> {
        val mF1ConstructorModels = gson.fromJson(jsonObject, F1Constructor::class.java)
        val result:ArrayList<DF1ConstructorModel> = ArrayList()
        var i = 0

        while (i < mF1ConstructorModels.mRData.standingsTable.standingsLists[0].constructorStandings.size) {
            val res:DF1ConstructorModel = DF1ConstructorModel(
                name = mF1ConstructorModels.mRData.standingsTable.standingsLists[0]
                    .constructorStandings[i].constructor.name,
                position = mF1ConstructorModels.mRData.standingsTable.standingsLists[0]
                    .constructorStandings[i].position,
                points = mF1ConstructorModels.mRData.standingsTable.standingsLists[0]
                    .constructorStandings[i].points,
                wins = mF1ConstructorModels.mRData.standingsTable.standingsLists[0]
                    .constructorStandings[i].wins,
                url = mF1ConstructorModels.mRData.standingsTable.standingsLists[0]
                    .constructorStandings[i].constructor.url
            )
            result.add(res)
            i++
        }
        return result
    }

}