package com.example.f1service

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.f1service.databinding.ActivityMainBinding
import com.example.f1service.extension.Const
import com.example.f1service.fragmentStateManager.FragmentStateManager
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mFragmentStateManager:FragmentStateManager



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val mFragmentManager = supportFragmentManager
        mFragmentStateManager = FragmentStateManager()
        mFragmentStateManager.setFragmentManager(mFragmentManager)

        Const.nextTime.observe(this) {
            setHomePage()
        }

        
        val mHomeNavigationBar: View = mBinding.bottomNavigationBar.findViewById(R.id.home)
        mHomeNavigationBar.performClick()

        val bottomNavigationBar: BottomNavigationView.OnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.race -> {
                        mFragmentStateManager.goRaceCalendar()
                        true
                    }
                    R.id.home -> {
                        setHomePage()
                        true
                    }
                    R.id.driver -> {
                        mFragmentStateManager.goDrivePage()
                        true
                    }
                    R.id.constructor -> {
                        mFragmentStateManager.goConstPage()
                        true
                    }
                    else -> false
                }
            }
        mBinding.bottomNavigationBar.setOnItemSelectedListener(bottomNavigationBar)
        mFragmentStateManager.goNextRace()
    }


    private fun setHomePage() {
        if (Const.nextTime.value?.raceTime != null
            && Const.nextTime.value?.qualitime != null
            && Const.currentTime != null) {

            if (Const.nextTime.value?.sprintTime != null) {

                if (Const.currentTime!! > Const.nextTime.value?.raceTime) {
                    mFragmentStateManager.goRacePage(Const.nextTime.value?.session,Const.nextTime.value?.round)
                }

                else if (Const.currentTime!! > Const.nextTime.value?.sprintTime) {
                    mFragmentStateManager.goSprintPage()
                }

                else if (Const.currentTime!! > Const.nextTime.value?.qualitime) {
                    mFragmentStateManager.goQualiftyPage(Const.nextTime.value?.session,Const.nextTime.value?.round)
                }

            }
            else {
                if (Const.currentTime!! > Const.nextTime.value?.raceTime) {
                    mFragmentStateManager.goRacePage(Const.nextTime.value?.session,Const.nextTime.value?.round)
                }

                else if (Const.currentTime!! > Const.nextTime.value?.qualitime) {
                    mFragmentStateManager.goQualiftyPage(Const.nextTime.value?.session,Const.nextTime.value?.round)
                }
                else {
                    mFragmentStateManager.goRacePage(Const.nextTime.value?.session,(Const.nextTime.value?.round?.toInt()?.minus(1)).toString())
                }
            }

        }

    }
}