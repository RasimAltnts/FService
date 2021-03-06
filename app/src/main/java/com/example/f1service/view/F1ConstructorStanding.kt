package com.example.f1service.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1service.adapter.ConsAdapter
import com.example.f1service.databinding.F1ConstructorStandingFragmentBinding
import com.example.f1service.service.IRequestCallback
import com.example.f1service.service.RestService
import com.google.gson.JsonObject

class F1ConstructorStanding : Fragment() {

    private lateinit var viewModel: F1ConstructorStandingViewModel
    private lateinit var mBinding:F1ConstructorStandingFragmentBinding
    private val mRestService = RestService()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = F1ConstructorStandingFragmentBinding.inflate(
            layoutInflater,
            container,
            false)

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(F1ConstructorStandingViewModel::class.java)

        viewModel.constructorList.observe(viewLifecycleOwner) {
            val adapter: ConsAdapter
            val layout: RecyclerView.LayoutManager =
                LinearLayoutManager(requireContext())
            mBinding.ConsRecyleView.layoutManager = layout
            adapter = ConsAdapter(it,requireContext())
            mBinding.ConsRecyleView.adapter = adapter

        }

        getDriverList()

    }

    val constCallback = object : IRequestCallback {
        override fun isSuccesfull(response: JsonObject?) {
            response?.let {
               viewModel.decodeResponse(response)
            }
        }

    }

    fun getDriverList() {
        mRestService.sendRequest("current/constructorStandings.json",constCallback)
    }
}