package com.example.f1service.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1service.F1.F1CircuitCountry
import com.example.f1service.databinding.RaceRowLayoutBinding
import com.example.f1service.extension.time
import com.example.f1service.model.F1CurrentSession
import com.example.f1service.model.F1CurrentSessionModel.Location
import java.time.ZonedDateTime
import kotlin.collections.ArrayList

class RaceListAdapter(var racelist:ArrayList<F1CurrentSession>,
                      var listener:IRaceList,
                      var context:Context):RecyclerView.Adapter<RaceListAdapter.RowHolder> () {


    interface IRaceList {
        fun onItemClick(round:String,session:String,date:String,time:String)
    }

    class RowHolder(var mBinding:RaceRowLayoutBinding):RecyclerView.ViewHolder(mBinding.root){
        private val mCountDownTimer = com.example.f1service.extension.CountDownTimer()
        private val mF1CircuitCountry = F1CircuitCountry()
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(racelist:ArrayList<F1CurrentSession>,
                 position: Int,
                 listener: IRaceList,
                 context: Context) {

            itemView.setOnClickListener {

                listener.onItemClick(
                    racelist[position].round,racelist[position].session,racelist[position].date,racelist[position].time)
            }
            mBinding.CircuitTextView.text = racelist[position].racename
            mBinding.dateTextview.text = racelist[position].date

            //Check Date Race Time
            val date = ZonedDateTime.now().time(racelist[position].date,
                racelist[position].time)

            val result = mCountDownTimer.checkDate(date)

            when(racelist[position].location.country) {
                "Bahrain" -> Glide.with(context).load(mF1CircuitCountry.bahreyh).into(mBinding.finishFlag)
                "Saudi Arabia" ->Glide.with(context).load(mF1CircuitCountry.saudiArabia).into(mBinding.finishFlag)
                "Australia" -> Glide.with(context).load(mF1CircuitCountry.australia).into(mBinding.finishFlag)
                "Italy" -> Glide.with(context).load(mF1CircuitCountry.italy).into(mBinding.finishFlag)
                "USA" -> Glide.with(context).load(mF1CircuitCountry.unitedStates).into(mBinding.finishFlag)
                "Spain" -> Glide.with(context).load(mF1CircuitCountry.spain).into(mBinding.finishFlag)
                "Monaco" -> Glide.with(context).load(mF1CircuitCountry.monaco).into(mBinding.finishFlag)
                "Azerbaijan" -> Glide.with(context).load(mF1CircuitCountry.azerbaijan).into(mBinding.finishFlag)
                "Canada" -> Glide.with(context).load(mF1CircuitCountry.canada).into(mBinding.finishFlag)
                "UK" -> Glide.with(context).load(mF1CircuitCountry.greatBritain).into(mBinding.finishFlag)
                "Austria" -> Glide.with(context).load(mF1CircuitCountry.austria).into(mBinding.finishFlag)
                "France" -> Glide.with(context).load(mF1CircuitCountry.france).into(mBinding.finishFlag)
                "Hungary" -> Glide.with(context).load(mF1CircuitCountry.hungary).into(mBinding.finishFlag)
                "Belgium" -> Glide.with(context).load(mF1CircuitCountry.belgium).into(mBinding.finishFlag)
                "Netherlands" -> Glide.with(context).load(mF1CircuitCountry.netherlands).into(mBinding.finishFlag)
                "Singapore" -> Glide.with(context).load(mF1CircuitCountry.singapore).into(mBinding.finishFlag)
                "Japan" -> Glide.with(context).load(mF1CircuitCountry.japan).into(mBinding.finishFlag)
                "Mexico" -> Glide.with(context).load(mF1CircuitCountry.mexico).into(mBinding.finishFlag)
                "Brazil" -> Glide.with(context).load(mF1CircuitCountry.brazil).into(mBinding.finishFlag)
                "Abu Dhabi" -> Glide.with(context).load(mF1CircuitCountry.abuDhabi).into(mBinding.finishFlag)
            }

            if (result) {
                itemView.alpha = 0.7f
            }

            else {
                itemView.alpha = 1f
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val mBinding = RaceRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(mBinding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(racelist,position,listener,context)
    }

    override fun getItemCount(): Int {
        return racelist.size
    }
}