package com.example.f1service.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.f1service.databinding.RaceRowLayoutBinding
import com.example.f1service.model.DF1CurrentSession
import com.example.f1service.model.F1CurrentSession
import java.util.*
import kotlin.collections.ArrayList

class RaceListAdapter(var racelist:ArrayList<F1CurrentSession>, var listener:IRaceList):RecyclerView.Adapter<RaceListAdapter.RowHolder> () {


    interface IRaceList {
        fun onItemClick(round:String,session:String,date:String,time:String)
    }

    class RowHolder(var mBinding:RaceRowLayoutBinding):RecyclerView.ViewHolder(mBinding.root){
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(racelist:ArrayList<F1CurrentSession>, position: Int, listener: IRaceList) {

            itemView.setOnClickListener {

                listener.onItemClick(racelist[position].round,racelist[position].session,racelist[position].date,racelist[position].time)
            }

            mBinding.CircuitTextView.text = racelist[position].racename
            mBinding.dateTextview.text = racelist[position].date

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val mBinding = RaceRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(mBinding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(racelist,position,listener)
    }

    override fun getItemCount(): Int {
        return racelist.size
    }
}