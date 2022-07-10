package com.example.f1service.model

import android.location.Location

data class DF1CurrentSession(
    val circuitName:String,
    val circuitId:String,
    val location: com.example.f1service.model.F1CurrentSessionModel.Location,
    val session:ArrayList<F1CurrentSession>
)


data class F1CurrentSession(
    var racename:String,
    var date:String,
    var time:String,
    var round:String,
    var session:String,
)
