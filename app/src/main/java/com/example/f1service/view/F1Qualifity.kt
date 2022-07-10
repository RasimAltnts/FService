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
import com.example.f1service.adapter.QualifyAdapter
import com.example.f1service.databinding.F1QualifityFragmentBinding

class F1Qualifity : Fragment() {

    private lateinit var viewModel: F1QualifityViewModel
    private lateinit var mBinding:F1QualifityFragmentBinding
    private lateinit var session:String
    private lateinit var round:String

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
        viewModel.getQualifying(session,round)

        viewModel.qualifity.observe(viewLifecycleOwner) {

            mBinding.raceTextView.text = it.circuitName

            val adapter:QualifyAdapter
            val layout: RecyclerView.LayoutManager =
                LinearLayoutManager(requireContext())
            mBinding.QualiftyRecycleView.layoutManager = layout
            adapter = QualifyAdapter(it.driverInfo,requireContext())
            mBinding.QualiftyRecycleView.adapter = adapter
        }
    }
}