package com.example.f1service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1service.F1.F1Cars
import com.example.f1service.F1.F1Team
import com.example.f1service.R
import com.example.f1service.databinding.ConsRowHolderBinding
import com.example.f1service.model.DF1ConstructorModel

class ConsAdapter(val list:ArrayList<DF1ConstructorModel>,val mContext: Context):RecyclerView.Adapter<ConsAdapter.RowHolder> () {

    class RowHolder(val mBinding:ConsRowHolderBinding):RecyclerView.ViewHolder(mBinding.root) {

        private val mF1Car = F1Cars()
        private val mF1Team = F1Team()

        fun bind(list:ArrayList<DF1ConstructorModel>,context:Context,position: Int) {
            mBinding.consNameTextView.text = list[position].name
            mBinding.position.text = list[position].position
            mBinding.pointsTextView.text = list[position].points + " P"

            when(list[position].name) {
                "Red Bull" -> {
                    Glide.with(context).load(mF1Car.redBullCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.redBullLogoURL).into(mBinding.consImageView)
                }

                "Ferrari" -> {
                    Glide.with(context).load(mF1Car.ferrariCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.ferrariLogoURL).into(mBinding.consImageView)
                }

                "Mercedes" -> {
                    Glide.with(context).load(mF1Car.mercedesCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.mercedesLogoURL).into(mBinding.consImageView)
                }

                "McLaren" -> {
                    Glide.with(context).load(mF1Car.mcLarenCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.mcLarenLogoURL).into(mBinding.consImageView)
                }

                "Alpine F1 Team" -> {
                    Glide.with(context).load(mF1Car.alpineCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.alphineLogoURL).into(mBinding.consImageView)
                }

                "Alfa Romeo" -> {
                    Glide.with(context).load(mF1Car.alfaRomeoCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.alfaLogoURL).into(mBinding.consImageView)
                }
                "AlphaTauri" -> {
                    Glide.with(context).load(mF1Car.alphaTauriCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.alphaTauriLogoURL).into(mBinding.consImageView)
                }
                "Aston Martin" -> {
                    Glide.with(context).load(mF1Car.astonMartinCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.astonMartinLogoURL).into(mBinding.consImageView)
                }
                "Haas F1 Team" -> {
                    Glide.with(context).load(mF1Car.haasCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.haasLogoURL).into(mBinding.consImageView)
                }
                "Williams" -> {
                    Glide.with(context).load(mF1Car.williamsCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.williamsLogoURL).into(mBinding.consImageView)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val mBinding = ConsRowHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(mBinding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
       holder.bind(list = list,context = mContext,position = position)
    }

    override fun getItemCount(): Int {
       return list.size
    }
}