package com.example.f1service

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.logic.MainActivityLogic
import com.example.f1service.model.DNextRaceModel
import com.example.f1service.service.RestService
import com.google.gson.JsonObject
import com.example.f1service.extension.timezone
import java.time.ZonedDateTime
import java.util.*

class MainActivityViewModel:ViewModel() {

    val nextRaceInfo: MutableLiveData<DNextRaceModel> by lazy {
        MutableLiveData<DNextRaceModel> ()
    }


    private var mRestService = RestService()
    private var mMainActivityLogic = MainActivityLogic()

    private var nextRaceCallback = object : RestService.IRequestCallback {
        @SuppressLint("CutPasteId", "SetTextI18n")
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
                nextRaceInfo.value = mMainActivityLogic.decodeNextRaceResponse(response)
            }
        }
    }


    fun isNextRace(){
        mRestService.sendRequest("current/next.json",nextRaceCallback)
    }
}