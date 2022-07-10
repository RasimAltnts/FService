package com.example.f1service.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1service.adapter.RaceListAdapter
import com.example.f1service.logic.CalendarListLogic
import com.example.f1service.model.DF1CurrentSession
import com.example.f1service.service.RestService
import com.google.gson.JsonObject

class RaceCalendarViewModel : ViewModel() {

    val calendar:MutableLiveData<DF1CurrentSession> by lazy {
        MutableLiveData<DF1CurrentSession> ()
    }

    private var mRestService = RestService()

    private var raceCallback = object : RestService.IRequestCallback{
        override fun isSuccesfull(response: JsonObject?) {

            val mCalendarListLogic = CalendarListLogic()
            calendar.value = response?.let { mCalendarListLogic.decodeResponse(jsonObject = it) }

        }

    }

    fun sendRequest() {
        mRestService.sendRequest("current.json",raceCallback)
    }
}