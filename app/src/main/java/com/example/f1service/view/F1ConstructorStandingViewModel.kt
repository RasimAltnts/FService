package com.example.f1service.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.logic.ConsListLogic
import com.example.f1service.model.DF1ConstructorModel
import com.example.f1service.service.IRequestCallback
import com.example.f1service.service.RestService
import com.google.gson.JsonObject

class F1ConstructorStandingViewModel : ViewModel() {

    val constructorList:MutableLiveData<ArrayList<DF1ConstructorModel>> by lazy {
        MutableLiveData<ArrayList<DF1ConstructorModel>> ()
    }

    private val mRestService = RestService()
    private val mConsListLogic = ConsListLogic()

    val constCallback = object : IRequestCallback {
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
                constructorList.value = mConsListLogic.decodeResponse(response)
            }
        }

    }

    fun getDriverList() {
        mRestService.sendRequest("current/constructorStandings.json",constCallback)
    }
}