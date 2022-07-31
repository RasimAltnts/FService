package com.example.f1service.extension

import androidx.lifecycle.MutableLiveData
import com.example.f1service.model.DNextWeekend
import java.util.*

class Const {
    companion object{
        val nextTime: MutableLiveData<DNextWeekend> by lazy {
            MutableLiveData<DNextWeekend>()
        }
    }
}