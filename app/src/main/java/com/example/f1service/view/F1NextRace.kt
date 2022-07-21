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
import com.example.f1service.extension.time
import com.example.f1service.extension.toString
import com.example.f1service.fragmentStateManager.FragmentStateManager
import com.example.f1service.model.DNextRaceModel
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
        mFragmentStateManager = FragmentStateManager()

        viewModel.nextRaceInfo.observe(viewLifecycleOwner) {
            mBinding.nextRaceTextView.text = it.nextRaceName
            mBinding.nextRaceLocationTextView.text = "${it.nextRaceCity},${it.nextRaceCountry}"

            //Inıtilaze Next Race Date

            Const.currentTime = Calendar.getInstance().time

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
            }

            Const.nextTime.value?.let {
                mBinding.nextRaceDateTextView.text = it.raceTime?.let { it1 -> ZonedDateTime.now().toString(timezone = it1) }
            }

            countDownTimerInit()

            startCountDownTimer()

        }

        DaggerIF1NextRace.builder()
            .restService(RestService())
            .build().inject(this)

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

    private fun countDownTimerInit() {
        countDownTimerMinutes()
        countDownTimerHours()
        countDownDays()
    }

    @OptIn(ExperimentalTime::class, DelicateCoroutinesApi::class)
    private fun startCountDownTimer() {
        val seconds = 60
        GlobalScope.launch(Dispatchers.Default) {
            while (true) {
                val currentSeconds: Int = Calendar.getInstance().time.seconds
                val countDownSeconds = seconds - currentSeconds
                withContext(Dispatchers.Main) {
                    mBinding.CalendarComponentSeconds.setText(countDownSeconds)
                    countDownTimerMinutes()
                }
            }
        }
    }

    private fun countDownTimerMinutes() {
        Const.nextTime.value?.raceTime?.let {
            val currentMinutes = Calendar.getInstance().time.minutes
            val dateMinutes = it.minutes
            val countTimeMinutes = 60 - (currentMinutes - dateMinutes)
            mBinding.calendarComponentsMinutes.setText(countTimeMinutes-1)
            if (countTimeMinutes == 60) {
                countDownTimerHours()
            }
        }
    }

    private fun countDownTimerHours() {
        Const.nextTime.value?.raceTime?.let {
            val currentHours = Calendar.getInstance().time.hours
            val dateHours = it.hours

            val countDownHours = dateHours - currentHours

            if (countDownHours >= 1) {
                mBinding.calendarComponentsHours.setText(countDownHours - 1)
            }
            else {
                mBinding.calendarComponentsHours.setText(24 + countDownHours - 1)
            }
        }
    }

    private fun countDownDays() {
        Const.nextTime.value?.raceTime?.let {
            val cal = Calendar.getInstance()
            val dayOfMonth: Int = cal.get(Calendar.DAY_OF_MONTH)
            val currentMonth = Calendar.getInstance().time.month
            val dateMonth = it.month
            val dateDays = it.date

            if (currentMonth == dateMonth) {
                if (dateDays <= dayOfMonth){
                    mBinding.CalendarComponetsDays.setText(0)
                }
                else {
                    mBinding.CalendarComponetsDays.setText(dateDays - dayOfMonth - 1 )
                }
            }
            else if (dateMonth > currentMonth) {
                mBinding.CalendarComponetsDays.setText(30 + (dateDays - dayOfMonth))
            }
        }
    }
}