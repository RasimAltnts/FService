package com.example.f1service.view

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.f1service.databinding.F1NextRaceFragmentBinding
import com.example.f1service.extension.Const
import com.example.f1service.extension.CountDownTimer
import com.example.f1service.extension.time
import com.example.f1service.extension.toString
import com.example.f1service.fragmentStateManager.FragmentStateManager
import com.example.f1service.model.DNextWeekend
import com.example.f1service.service.DaggerIF1NextRace
import com.example.f1service.service.IRequestCallback
import com.example.f1service.service.RestService
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class F1NextRace : Fragment() {

    private lateinit var viewModel: F1NextRaceViewModel
    private lateinit var mBinding:  F1NextRaceFragmentBinding
    private lateinit var mFragmentStateManager: FragmentStateManager
    private lateinit var mCountDownTimer: CountDownTimer


    @Inject
    lateinit var restService: RestService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = F1NextRaceFragmentBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[F1NextRaceViewModel::class.java]
        mFragmentStateManager = FragmentStateManager.getInstance()
        mCountDownTimer = CountDownTimer()

        DaggerIF1NextRace.builder()
            .restService(RestService())
            .build().inject(this)


        val mICountDownTimer = object : CountDownTimer.ICountDownTimer {
            override fun getDays(days: Long) {
                mBinding.CalendarComponetsDays.setText(days.toInt())
            }

            override fun getHours(hours: Long) {
                mBinding.calendarComponentsHours.setText(hours.toInt())
            }

            override fun getMinutes(minutes: Long) {
                mBinding.calendarComponentsMinutes.setText(minutes.toInt())
            }

            override fun getSeconds(seconds: Long) {
                mBinding.CalendarComponentSeconds.setText(seconds.toInt())
            }
        }

        mCountDownTimer.setListener(listener = mICountDownTimer)

        viewModel.nextRaceInfo.observe(viewLifecycleOwner) {
            mBinding.nextRaceTextView.text = it.nextRaceName
            mBinding.nextRaceLocationTextView.text = "${it.nextRaceCity},${it.nextRaceCountry}"


            it?.let {
                val nextWeekModel = DNextWeekend()
                nextWeekModel.raceTime = ZonedDateTime.now().time(it.nextRaceDate,it.nextRaceTime)
                nextWeekModel.qualitime = ZonedDateTime.now().time(it.qualifying.date,it.qualifying.time)
                nextWeekModel.session = it.nextRaceYear
                nextWeekModel.round = it.nextRaceRound
                if (it.sprintDate != null && it.sprintTime != null) {
                    nextWeekModel.sprintTime = ZonedDateTime.now().time(it.sprintDate,it.sprintTime)
                }

                Const.nextTime.value = nextWeekModel


                mCountDownTimer.startTimer(ZonedDateTime.now().time(it.nextRaceDate,it.nextRaceTime))
            }

            Const.nextTime.value?.let {
                mBinding.nextRaceDateTextView.text = it.raceTime?.let { it1 -> ZonedDateTime.now().toString(timezone = it1) }
            }
        }

        isNextRace()
    }

    private var nextRaceCallback = object : IRequestCallback {
        @SuppressLint("CutPasteId", "SetTextI18n")
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
                viewModel.decodeNextRaceResponse(response)
            }
        }
    }

    private fun isNextRace(){
        restService.sendRequest("current/next.json",nextRaceCallback)
    }

    private fun startTimer(date:Date) {
        //mCountDownTimer.getTime(date)
    }

}