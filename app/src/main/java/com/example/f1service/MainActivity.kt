package com.example.f1service

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.f1service.databinding.ActivityMainBinding
import com.example.f1service.extension.time
import com.example.f1service.extension.toString
import com.example.f1service.fragmentStateManager.FragmentStateManager
import com.example.f1service.model.DNextRaceModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*
import java.time.ZonedDateTime
import java.util.*
import kotlin.time.ExperimentalTime


class MainActivity : AppCompatActivity() {


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mMainActivityViewModel:MainActivityViewModel
    private lateinit var mFragmentStateManager:FragmentStateManager
    private lateinit var date:Date
    private lateinit var qualifity:Date
    private lateinit var currentTime:Date
    private lateinit var raceModel:DNextRaceModel


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mMainActivityViewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mMainActivityViewModel.nextRaceInfo.observe(this) {
            println("nextRacce:$it")
            raceModel = it
            mBinding.nextRaceTextView.text = it.nextRaceName
            mBinding.nextRaceLocationTextView.text = "${it.nextRaceCity},${it.nextRaceCountry}"

            //Inıtilaze Next Race Date
            date = ZonedDateTime.now().time(it.nextRaceDate,it.nextRaceTime)
            qualifity = ZonedDateTime.now().time(it.qualifying.date,it.qualifying.time)
            mBinding.nextRaceDateTextView.text = ZonedDateTime.now().toString(timezone = date)
            countDownTimerInit()

            currentTime = Calendar.getInstance().time
            val mFragmentManager = supportFragmentManager
            mFragmentStateManager = FragmentStateManager(mFragmentManager)
            startCountDownTimer()


            val mHomeNavigationBar: View = mBinding.bottomNavigationBar.findViewById(R.id.home)
            mHomeNavigationBar.performClick()
       }

        mMainActivityViewModel.isNextRace()


        val bottomNavigationBar: BottomNavigationView.OnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.race -> {
                        FragmentStateManager.goRacePage()
                        true
                    }
                    R.id.home -> {
                        if (currentTime.time > qualifity.time) {
                            FragmentStateManager.goQualiftyPage(raceModel.nextRaceYear,raceModel.nextRaceRound)
                        }
                        else {
                            FragmentStateManager.goHomePage(raceModel.nextRaceYear,(raceModel.nextRaceRound.toInt() - 1).toString())
                        }
                        true
                    }
                    R.id.driver -> {
                        FragmentStateManager.goDrivePage()
                        true
                    }
                    R.id.constructor -> {
                        FragmentStateManager.goConstPage()
                        true
                    }
                    else -> false
                }
            }


        mBinding.bottomNavigationBar.setOnItemSelectedListener(bottomNavigationBar)
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
        val currentMinutes = Calendar.getInstance().time.minutes
        val dateMinutes = date.minutes
        val countTimeMinutes = 60 - (currentMinutes - dateMinutes)
        mBinding.calendarComponentsMinutes.setText(countTimeMinutes-1)
        if (countTimeMinutes == 60) {
            countDownTimerHours()
        }
    }

    private fun countDownTimerHours() {
        val currentHours = Calendar.getInstance().time.hours
        val dateHours = date.hours

        val countDownHours = dateHours - currentHours

        if (countDownHours >= 1) {
            mBinding.calendarComponentsHours.setText(countDownHours - 1)
        }
        else {
            mBinding.calendarComponentsHours.setText(24 + countDownHours - 1)
        }
    }

    private fun countDownDays() {
        val cal = Calendar.getInstance()
        val dayOfMonth: Int = cal.get(Calendar.DAY_OF_MONTH)
        val currentMonth = Calendar.getInstance().time.month
        val dateMonth = date.month
        val dateDays = date.date

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