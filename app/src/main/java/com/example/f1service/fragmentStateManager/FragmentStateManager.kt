package com.example.f1service.fragmentStateManager

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.f1service.R
import com.example.f1service.view.*

class FragmentStateManager(var FragmentManager:FragmentManager) {

    companion object {
        private var fragmentManager:FragmentManager? = null


        fun goRacePage() {
            var fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.homeLayout, RaceCalendar())
            fragmentTransaction?.commit()
        }

        fun goHomePage(session: String,round:String) {
            var fragmentTransaction = fragmentManager?.beginTransaction()
            val bundle = Bundle()
            bundle.putString("session",session)
            bundle.putString("round",round)
            val homepage = Homepage()
            homepage.arguments = bundle
            fragmentTransaction?.replace(R.id.homeLayout,homepage)
            fragmentTransaction?.commit()
        }

        fun goDrivePage() {
            var fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.homeLayout,F1Drivers())
            fragmentTransaction?.commit()
        }

        fun goConstPage() {
            var fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.homeLayout,F1ConstructorStanding())
            fragmentTransaction?.commit()
        }

        fun goQualiftyPage(session:String,round:String) {
            var fragmentTransaction = fragmentManager?.beginTransaction()
            val bundle = Bundle()
            bundle.putString("session",session)
            bundle.putString("round",round)
            val mF1Qualifity = F1Qualifity()
            mF1Qualifity.arguments = bundle
            fragmentTransaction?.replace(R.id.homeLayout,mF1Qualifity)
            fragmentTransaction?.commit()
        }
    }

    init {
        fragmentManager = FragmentManager
    }
}