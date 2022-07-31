package com.example.f1service.view

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1service.R
import com.example.f1service.adapter.QualifyAdapter
import com.example.f1service.databinding.F1QualifityFragmentBinding
import com.example.f1service.extension.Const
import com.example.f1service.fragmentStateManager.FragmentStateManager
import com.example.f1service.model.DF1Qualifying
import com.example.f1service.service.IRequestCallback
import com.example.f1service.service.RestService
import com.google.gson.JsonObject

class F1Qualifity : Fragment() {

    private lateinit var viewModel: F1QualifityViewModel
    private lateinit var mBinding:F1QualifityFragmentBinding
    private var mFragmentStateManager = FragmentStateManager.getInstance()
    private lateinit var session:String
    private lateinit var round:String
    private var mRestService = RestService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        session = bundle?.getString("session").toString()
        round = bundle?.getString("round").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         mBinding = F1QualifityFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(F1QualifityViewModel::class.java)
        getQualifying(session,round)

        viewModel.qualifity.observe(viewLifecycleOwner) {
            if (it != null) {
                initQualifyLayout(it)
            }
            else {
                mFragmentStateManager.goRacePage(Const.nextTime.value?.session,
                    (Const.nextTime.value?.round?.toInt()?.minus(1)).toString())
            }
        }
    }

    private var qualifyingCallback = object : IRequestCallback {
        @SuppressLint("CutPasteId", "SetTextI18n")
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
                viewModel.decodeResponse(response)
            }
        }
    }

    fun getQualifying(session:String,round:String) {
        mRestService.sendRequest("$session/$round/qualifying.json",qualifyingCallback)
    }

    fun initQualifyLayout(data:DF1Qualifying?) {
        mBinding.raceTextView.text = data?.circuitName

        val adapter:QualifyAdapter
        val layout: RecyclerView.LayoutManager =
            LinearLayoutManager(requireContext())
        mBinding.QualiftyRecycleView.layoutManager = layout
        adapter = QualifyAdapter(data!!.driverInfo,requireContext())
        mBinding.QualiftyRecycleView.adapter = adapter
    }
}