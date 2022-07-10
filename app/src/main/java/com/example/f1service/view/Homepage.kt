package com.example.f1service.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1service.adapter.LastRaceAdapter
import com.example.f1service.databinding.HomepageFragmentBinding
import com.example.f1service.fragmentStateManager.FragmentStateManager
import java.lang.Exception

class Homepage : Fragment() {

    private lateinit var viewModel: HomepageViewModel
    private lateinit var mBinding:HomepageFragmentBinding
    private lateinit var session:String
    private lateinit var round:String

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
        viewModel.lastRaceInfo.observe(requireActivity()) {
            if (it.pilot == null) {
                FragmentStateManager.goQualiftyPage(session,round)
            }
            else {
                mBinding.lastRaceTextView.text = it.circuitName

                it.pilot?.let {
                    val adapter: LastRaceAdapter
                    val layout: RecyclerView.LayoutManager =
                        LinearLayoutManager(requireContext())
                    mBinding.lastRaceRecycleView.layoutManager = layout
                    adapter = LastRaceAdapter(it,requireContext())
                    mBinding.lastRaceRecycleView.adapter = adapter
                }
            }
        }

        try {
            viewModel.getLastRace(session,round)
        }catch (e:Exception) {
            println(e.localizedMessage)
        }

    }

}