package com.example.f1service.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1service.adapter.RaceListAdapter
import com.example.f1service.databinding.RaceCalendarFragmentBinding
import com.example.f1service.extension.time
import com.example.f1service.fragmentStateManager.FragmentStateManager
import com.example.f1service.service.IRequestCallback
import com.example.f1service.service.RestService
import com.google.gson.JsonObject
import java.time.ZonedDateTime
import java.util.*

class RaceCalendar : Fragment(),RaceListAdapter.IRaceList {

    private lateinit var viewModel: RaceCalendarViewModel
    private lateinit var mBinding: RaceCalendarFragmentBinding
    private var currentTime = Calendar.getInstance().time
    private lateinit var mFragmentStateManager: FragmentStateManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = RaceCalendarFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RaceCalendarViewModel::class.java)
        mFragmentStateManager = FragmentStateManager()

        sendRequest()

        viewModel.calendar.observe(viewLifecycleOwner) {

            val adapter: RaceListAdapter
            val layout: RecyclerView.LayoutManager =
                LinearLayoutManager(requireContext())
            mBinding.racelistRecycleView.layoutManager = layout
            adapter = RaceListAdapter(it.session,this)
            mBinding.racelistRecycleView.adapter = adapter
        }
    }

    override fun onItemClick(round: String, session: String, date: String, time: String) {
        val timezone = ZonedDateTime.now().time(date,time)
        if (currentTime.time > timezone.time){
            mFragmentStateManager.goRacePage(session,round)
        }
    }

    private var mRestService = RestService()

    private var raceCallback = object : IRequestCallback {
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {viewModel.decodeResponse(jsonObject = it) }
        }
    }

    fun sendRequest() {
        mRestService.sendRequest("current.json",raceCallback)
    }
}