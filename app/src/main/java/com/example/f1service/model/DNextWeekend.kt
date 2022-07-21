package com.example.f1service.model

import java.util.*

data class DNextWeekend(
    var raceTime: Date? = null,
    var qualitime:Date?= null,
    var sprintTime:Date? = null,
    var round:String? = "2022",
    var session:String? = "11"
)