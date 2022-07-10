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
import com.example.f1service.adapter.DriverListAdapter
import com.example.f1service.adapter.LastRaceAdapter
import com.example.f1service.databinding.F1DriversFragmentBinding

class F1Drivers : Fragment() {

    private lateinit var viewModel: F1DriversViewModel
    private lateinit var mBinding: F1DriversFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = F1DriversFragmentBinding.inflate(
            layoutInflater,
            container,
            false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(F1DriversViewModel::class.java)
        viewModel.getDriverList()

        viewModel.driverList.observe(viewLifecycleOwner) {

            val adapter: DriverListAdapter
            val layout: RecyclerView.LayoutManager =
                LinearLayoutManager(requireContext())
            mBinding.driverRecycleView.layoutManager = layout
            adapter = DriverListAdapter(it,requireContext())
            mBinding.driverRecycleView.adapter = adapter
        }
    }

}