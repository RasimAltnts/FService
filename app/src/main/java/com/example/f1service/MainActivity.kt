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

        //--Test
        mFragmentStateManager.goSprintPage()


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
                        if (Const.raceTime != null
                            && Const.qualifityTime != null
                            && Const.currentTime != null) {

                            if (Const.sprintTime != null) {

                                if (Const.currentTime!! > Const.raceTime) {
                                    mFragmentStateManager.goRacePage(Const.session,Const.round)
                                }

                                else if (Const.currentTime!! > Const.sprintTime) {
                                    mFragmentStateManager.goSprintPage()
                                }

                                else if (Const.currentTime!! > Const.qualifityTime) {
                                    mFragmentStateManager.goQualiftyPage(Const.session,Const.round)
                                }

                            }
                            else {
                                if (Const.currentTime!! > Const.raceTime) {
                                    mFragmentStateManager.goRacePage(Const.session,Const.round)
                                }

                                else if (Const.currentTime!! > Const.qualifityTime) {
                                    mFragmentStateManager.goQualiftyPage(Const.session,Const.round)
                                }
                                else {
                                    mFragmentStateManager.goRacePage(Const.session,(Const.round.toInt()-1).toString())
                                }
                            }

                        }
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
}