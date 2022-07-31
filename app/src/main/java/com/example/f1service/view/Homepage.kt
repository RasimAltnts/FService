package com.example.f1service.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1service.adapter.LastRaceAdapter
import com.example.f1service.databinding.HomepageFragmentBinding
import com.example.f1service.extension.Const
import com.example.f1service.fragmentStateManager.FragmentStateManager
import com.example.f1service.model.DLastRace
import com.example.f1service.service.IRequestCallback
import com.example.f1service.service.RestService
import com.google.gson.JsonObject
import java.lang.Exception

class Homepage : Fragment() {

    private lateinit var viewModel: HomepageViewModel
    private lateinit var mBinding:HomepageFragmentBinding
    private lateinit var session:String
    private lateinit var round:String
    private lateinit var mFragmentStateManager: FragmentStateManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val bundle = arguments
        session = bundle?.getString("session").toString()
        round = bundle?.getString("round").toString()

        mBinding = HomepageFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomepageViewModel::class.java)
        mFragmentStateManager = FragmentStateManager.getInstance()

        viewModel.lastRaceInfo.observe(requireActivity()) {
            if (it != null) {
                initLastRaceLayout(it)
            }
            else {
                if (Const.nextTime.value?.sprintTime != null) {
                    mFragmentStateManager.goSprintPage()
                }
                else {
                    mFragmentStateManager.goQualiftyPage(Const.nextTime.value?.session,
                        Const.nextTime.value?.round)
                }
            }
        }

        getLastRace(session,round)

    }

    private var mRestService = RestService()


    private var lastRaceResultCallback: IRequestCallback = object : IRequestCallback {
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
                viewModel.decodeLastRaceResponse(response)
            }
        }
    }

    fun getLastRace(session:String,round:String) {
        mRestService.sendRequest("$session/$round/results.json",lastRaceResultCallback)
    }

    fun initLastRaceLayout(data:DLastRace) {
        mBinding.lastRaceTextView.text = data.circuitName.toString()
        try {
            data.pilot?.let {
                val adapter: LastRaceAdapter
                val layout: RecyclerView.LayoutManager =
                    LinearLayoutManager(requireContext())
                mBinding.lastRaceRecycleView.layoutManager = layout
                adapter = LastRaceAdapter(it,requireContext())
                mBinding.lastRaceRecycleView.adapter = adapter
            }

        }catch (e:Exception) {
            Log.d("I/List","RecycleView Error :${e.cause}")
        }
    }

}