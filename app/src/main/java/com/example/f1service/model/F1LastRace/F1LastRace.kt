package com.example.f1service.model.F1LastRace


import com.google.gson.annotations.SerializedName

data class F1LastRace(
    @SerializedName("MRData")
    val mRData: MRData
)