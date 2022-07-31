package com.example.f1service.extension

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.util.*

class CountDownTimer() {

    interface ICountDownTimer {
        fun getDays(days: Long)
        fun getHours(hours: Long)
        fun getMinutes(minutes: Long)
        fun getSeconds(seconds: Long)
    }

    private val daysMilliseconds = 86400000
    private val hoursMilliseconds = 3600000
    private val minutesMilliseconds = 60000
    private val secondsMilliseconds = 1000

    private var stopTimer = false

    private lateinit var listener:ICountDownTimer


    @OptIn(DelicateCoroutinesApi::class)
    fun startTimer(date:Date){
        GlobalScope.launch(Dispatchers.Default) {
            while (true) {
                if (stopTimer) {
                    break
                }
                else {
                    getTime(date)
                }
            }
        }
    }


    suspend fun getTime(date:Date) {
        val time: Calendar = Calendar.getInstance()
        val timeMilliseconds = time.time
        val dateMilliseconds = date.time
        val diff: Long = dateMilliseconds - timeMilliseconds.time

        val days = diff / daysMilliseconds
        val remainderDays = diff % daysMilliseconds
        val hours = remainderDays / hoursMilliseconds
        val remainderHours = remainderDays % hoursMilliseconds
        val minutes = remainderHours / minutesMilliseconds
        val remainderMinutes = remainderHours % minutesMilliseconds
        val seconds = remainderMinutes / secondsMilliseconds

        withContext(Main) {
            if (days <= 0) {
                listener.getDays(0)
            }
            else{
                listener.getDays(days)
            }

            if (hours <= 0) {
                listener.getHours(0)

            }

            else {
                listener.getHours(hours)
            }

            if (minutes <= 0) {
                listener.getMinutes(0)
            }

            else {
                listener.getMinutes(minutes)

            }
            if (seconds <= 0) {
                listener.getSeconds(0)
            }
            else {
                listener.getSeconds(seconds)
            }

        }
    }

    fun setListener(listener:ICountDownTimer) {
        this.listener = listener
    }

    fun stopTimer(){
        stopTimer = true
    }


    fun checkDate(date: Date):Boolean {
        val currentTime = Calendar.getInstance().time
        var result:Boolean = false
        result = currentTime.time > date.time
        return result
    }
}