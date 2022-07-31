package com.example.f1service.fragmentStateManager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.f1service.R
import com.example.f1service.view.*

class FragmentStateManager private constructor(){

    companion object {
        var frgManager:FragmentStateManager ?= null

        fun getInstance():FragmentStateManager {
            if (frgManager == null) {
                frgManager = FragmentStateManager()
                return frgManager!!
            }
            return frgManager!!
        }

    }

    private var frgManager:FragmentManager? = null

    fun init(fragmentManager: FragmentManager) {
        frgManager = fragmentManager
    }

    fun goRaceCalendar() {
        try{
            val fragmentTransaction = frgManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.homeLayout, RaceCalendar())
            fragmentTransaction?.commit()
        }catch (e:Exception){
            Log.d("I/FService","Fragment Transaction error because ${e.cause}")
        }
    }

    fun goRacePage(session: String?, round:String?) {
        try {
            val fragmentTransaction = frgManager?.beginTransaction()
            val bundle = Bundle()
            bundle.putString("session",session)
            bundle.putString("round",round)
            val homepage = Homepage()
            homepage.arguments = bundle
            fragmentTransaction?.replace(R.id.homeLayout,homepage)
            fragmentTransaction?.commit()
        }catch (e:Exception){
            Log.d("I/FService","Fragment Transaction error because ${e.cause}")
        }
    }

    fun goDrivePage() {
        try {
            val fragmentTransaction = frgManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.homeLayout,F1Drivers())
            fragmentTransaction?.commit()
        }catch (e:Exception){
            Log.d("I/FService","Fragment Transaction error because ${e.cause}")
        }

    }

    fun goConstPage() {
        try {
            val fragmentTransaction = frgManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.homeLayout,F1ConstructorStanding())
            fragmentTransaction?.commit()
        }catch (e:Exception) {
            Log.d("I/FService","Fragment Transaction error because ${e.cause}")

        }

    }

    fun goQualiftyPage(session:String?,round:String?) {
        try {
            val fragmentTransaction = frgManager?.beginTransaction()
            val bundle = Bundle()
            bundle.putString("session",session)
            bundle.putString("round",round)
            val mF1Qualifity = F1Qualifity()
            mF1Qualifity.arguments = bundle
            fragmentTransaction?.replace(R.id.homeLayout,mF1Qualifity)
            fragmentTransaction?.commit()
        }catch (e:Exception) {
            Log.d("I/FService","Fragment Transaction error because ${e.cause}")

        }
    }

    fun goNextRace() {
        try {
            val fragmentTransaction = frgManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nextRaceLay,F1NextRace())
            fragmentTransaction?.commit()
        }catch (e:Exception){
            Log.d("I/FService","Fragment Transaction error because ${e.cause}")
        }
    }

    fun goSprintPage() {
        try {
            val fragmentTransaction = frgManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.homeLayout,F1Sprint())
            fragmentTransaction?.commit()
        }catch (e:Exception) {
            Log.d("I/FService","Fragment Transaction error because ${e.cause}")
        }
    }
}