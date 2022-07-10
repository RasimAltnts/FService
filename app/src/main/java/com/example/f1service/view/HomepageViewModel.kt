package com.example.f1service.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.logic.HomePageLogic
import com.example.f1service.model.DLastRace
import com.example.f1service.service.RestService
import com.google.gson.JsonObject

class HomepageViewModel : ViewModel() {

    val lastRaceInfo: MutableLiveData<DLastRace> by lazy {
        MutableLiveData<DLastRace> ()
    }

    private var mRestService = RestService()
    private var mHomepageLogic = HomePageLogic()


    private var lastRaceResultCallback: RestService.IRequestCallback = object : RestService.IRequestCallback{
        override fun isSuccesfull(response: JsonObject?) {
            println("res:$response")
            response?.let {
                lastRaceInfo.value = mHomepageLogic.decodeLastRaceResponse(response)
            }
        }
    }

    fun getLastRace(session:String,round:String) {
        println("session:$session race:$round")
                mRestService.sendRequest("$session/$round/results.json",lastRaceResultCallback)
    }
}