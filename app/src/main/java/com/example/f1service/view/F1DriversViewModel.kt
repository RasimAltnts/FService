package com.example.f1service.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1service.logic.DriverListLogic
import com.example.f1service.model.DF1DriversModels
import com.example.f1service.service.RestService
import com.google.gson.JsonObject

class F1DriversViewModel : ViewModel() {
    val driverList:MutableLiveData<ArrayList<DF1DriversModels>> by lazy {
        MutableLiveData<ArrayList<DF1DriversModels>> ()
    }

    private val mRestService = RestService()
    private val mF1DriverListLogic = DriverListLogic()

    private val driverListResponse = object : RestService.IRequestCallback {
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
                driverList.value = mF1DriverListLogic.decodeResponse(response)
            }
        }
    }

    fun getDriverList() {
        mRestService.sendRequest("2022/driverStandings.json",driverListResponse)
    }
}