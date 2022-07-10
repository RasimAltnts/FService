package com.example.f1service.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1service.F1.F1Cars
import com.example.f1service.F1.F1Team
import com.example.f1service.R
import com.example.f1service.databinding.ConsRowHolderBinding
import com.example.f1service.databinding.QualifyRowHolderBinding
import com.example.f1service.model.DF1ConstructorModel
import com.example.f1service.model.DQualityDriver

class QualifyAdapter(val list:ArrayList<DQualityDriver>,val mContext: Context):RecyclerView.Adapter<QualifyAdapter.RowHolder> () {

    class RowHolder(val mBinding:QualifyRowHolderBinding):RecyclerView.ViewHolder(mBinding.root) {
        private val mF1Team = F1Team()
        @SuppressLint("SetTextI18n")
        fun bind(list:ArrayList<DQualityDriver>, context:Context, position: Int) {
            mBinding.driverName.text = "${list[position].driverName} ${list[position].driverSurname}"
            mBinding.position.text = list[position].position
            mBinding.q1TextView.text = list[position].q1Time
            mBinding.q3TextView.text = list[position].q3Time

            list[position].q2Time?.let {
                mBinding.q2TextView.text = it
            }


            when(list[position].consId) {
                "Red Bull" -> {
                    Glide.with(context).load(mF1Team.redBullLogoURL).into(mBinding.teamImageView)
                }

                "Ferrari" -> {
                    Glide.with(context).load(mF1Team.ferrariLogoURL).into(mBinding.teamImageView)
                }

                "Mercedes" -> {
                    Glide.with(context).load(mF1Team.mercedesLogoURL).into(mBinding.teamImageView)
                }

                "McLaren" -> {
                    Glide.with(context).load(mF1Team.mcLarenLogoURL).into(mBinding.teamImageView)
                }

                "Alpine F1 Team" -> {
                    Glide.with(context).load(mF1Team.alphineLogoURL).into(mBinding.teamImageView)
                }

                "Alfa Romeo" -> {
                    Glide.with(context).load(mF1Team.alfaLogoURL).into(mBinding.teamImageView)
                }
                "AlphaTauri" -> {
                    Glide.with(context).load(mF1Team.alphaTauriLogoURL).into(mBinding.teamImageView)
                }
                "Aston Martin" -> {
                    Glide.with(context).load(mF1Team.astonMartinLogoURL).into(mBinding.teamImageView)
                }
                "Haas F1 Team" -> {
                    Glide.with(context).load(mF1Team.haasLogoURL).into(mBinding.teamImageView)
                }
                "Williams" -> {
                    Glide.with(context).load(mF1Team.williamsLogoURL).into(mBinding.teamImageView)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val mBinding = QualifyRowHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(mBinding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(list = list,context = mContext,position = position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}