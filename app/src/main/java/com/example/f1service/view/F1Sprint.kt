package com.example.f1service.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1service.R
import com.example.f1service.adapter.SprintAdapter
import com.example.f1service.databinding.F1SprintFragmentBinding
import com.example.f1service.extension.Const
import com.example.f1service.fragmentStateManager.FragmentStateManager
import com.example.f1service.model.DF1SprintModels
import com.example.f1service.service.IRequestCallback
import com.example.f1service.service.RestService
import com.google.gson.JsonObject
import java.util.logging.Logger

class F1Sprint : Fragment() {

    private lateinit var viewModel: F1SprintViewModel
    private lateinit var mFragmentStateManager:FragmentStateManager
    private lateinit var mRestService: RestService
    private lateinit var mBinding:F1SprintFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = F1SprintFragmentBinding.inflate(layoutInflater,
                    container,
            false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(F1SprintViewModel::class.java)
        mFragmentStateManager = FragmentStateManager.getInstance()
        mRestService = RestService()
        sendRequest()

        viewModel.sprintLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                initSprintRaceLayout(it)
            }
            else {
                mFragmentStateManager.goQualiftyPage(Const.nextTime.value!!.session,
                    Const.nextTime.value!!.round)
            }
        }
    }

    private var callback:IRequestCallback = object : IRequestCallback {
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
                viewModel.decodeResponse(response)
            }
        }
    }

    private fun sendRequest(){
        mRestService.sendRequest(
            "${Const.nextTime.value?.session}/${Const.nextTime.value?.round}/sprint.json",
            callback)
    }

    private fun initSprintRaceLayout(data:DF1SprintModels) {
        val adapter:SprintAdapter
        val layoutManager:RecyclerView.LayoutManager =
            LinearLayoutManager(requireContext())
        mBinding.SprintRecycleView.layoutManager = layoutManager
        adapter = SprintAdapter(data.sprintDriver,requireContext())
        mBinding.SprintRecycleView.adapter = adapter
    }
}