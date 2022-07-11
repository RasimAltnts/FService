package com.example.f1service.view

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.logic.QualifyingLogic
import com.example.f1service.model.DF1Qualifying
import com.example.f1service.service.IRequestCallback
import com.example.f1service.service.RestService
import com.google.gson.JsonObject

class F1QualifityViewModel : ViewModel() {


    val qualifity:MutableLiveData<DF1Qualifying> by lazy {
        MutableLiveData<DF1Qualifying> ()
    }

    private var mRestService = RestService()
    private var mLogic = QualifyingLogic()

    private var qualifyingCallback = object : IRequestCallback {
        @SuppressLint("CutPasteId", "SetTextI18n")
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
                println("response:$response")
               qualifity.value = mLogic.decodeResponse(response)
            }
        }
    }

    fun getQualifying(session:String,round:String) {
        mRestService.sendRequest("$session/$round/qualifying.json",qualifyingCallback)
    }
}