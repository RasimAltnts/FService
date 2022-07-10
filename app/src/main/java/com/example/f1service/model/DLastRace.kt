package com.example.f1service.model

data class DLastRace(
    var circuitName: String?,
    var circuitId: String?,
    var pilot: ArrayList<DLastRaceResult>?
)

data class DLastRaceResult(
    var position:String,
    var carNumber:String,
    var point:String,
    var pilotName:String,
    var pilotSurname:String,
    var constructorName:String,
    var constructorId:String,
    var driverId:String,
    var fastestLap:String?,
)
