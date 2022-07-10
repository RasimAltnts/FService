package com.example.f1service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1service.F1.F1Driver
import com.example.f1service.F1.F1DriverNumber
import com.example.f1service.F1.F1Team
import com.example.f1service.R
import com.example.f1service.databinding.DriverRowHolderBinding
import com.example.f1service.model.DF1DriversModels

class DriverListAdapter(val list:ArrayList<DF1DriversModels>,val mContext: Context):RecyclerView.Adapter<DriverListAdapter.RowHolder>() {

    class RowHolder(val mBinding:DriverRowHolderBinding):RecyclerView.ViewHolder(mBinding.root) {

        var mF1Driver = F1Driver()
        var mF1Team = F1Team()

        fun bind(list:ArrayList<DF1DriversModels>,position: Int,context: Context) {

            mBinding.position.text = list[position].position
            mBinding.driverName.text = "${list[position].pilotName} ${list[position].pilotSurname}"
            mBinding.driverPoints.text = list[position].points + " P"


            when(list[position].pilotName) {
                "Max" -> {
                    Glide.with(context).load(mF1Driver.maxVerstappen).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.redBullLogoURL).into(mBinding.driverNumber)

                }
                "Sergio" -> {
                    Glide.with(context).load(mF1Driver.sergioPerez).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.redBullLogoURL).into(mBinding.driverNumber)
                }

                "Charles" -> {
                    Glide.with(context).load(mF1Driver.charlesLeclerc).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.ferrariLogoURL).into(mBinding.driverNumber)
                }

                "George" -> {
                    Glide.with(context).load(mF1Driver.georgeRussell).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.mercedesLogoURL).into(mBinding.driverNumber)
                }
                "Carlos" -> {
                    Glide.with(context).load(mF1Driver.carlosSainz).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.ferrariLogoURL).into(mBinding.driverNumber)
                }
                "Lewis" -> {
                    Glide.with(context).load(mF1Driver.lewisHamilton).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.mercedesLogoURL).into(mBinding.driverNumber)
                }
                "Lando" -> {
                    Glide.with(context).load(mF1Driver.landoNorris).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.mcLarenLogoURL).into(mBinding.driverNumber)
                }
                "Valtteri" -> {
                    Glide.with(context).load(mF1Driver.valtteriBottas).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.alfaLogoURL).into(mBinding.driverNumber)
                }
                "Esteban" -> {
                    Glide.with(context).load(mF1Driver.estebanOcon).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.alphineLogoURL).into(mBinding.driverNumber)
                }
                "Fernando" -> {
                    Glide.with(context).load(mF1Driver.fernandoAlanso).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.alphineLogoURL).into(mBinding.driverNumber)
                }
                "Pierre" -> {
                    Glide.with(context).load(mF1Driver.pierreGasly).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.alphaTauriLogoURL).into(mBinding.driverNumber)
                }
                "Kevin" -> {
                    Glide.with(context).load(mF1Driver.kevinMagnussen).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.haasLogoURL).into(mBinding.driverNumber)
                }
                "Daniel" -> {
                    Glide.with(context).load(mF1Driver.danielRiccardo).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.mcLarenLogoURL).into(mBinding.driverNumber)
                }
                "Sebastian" -> {
                    Glide.with(context).load(mF1Driver.sebastianVettel).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.astonMartinLogoURL).into(mBinding.driverNumber)
                }
                "Yuki" -> {
                    Glide.with(context).load(mF1Driver.yukiTsunoda).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.alphaTauriLogoURL).into(mBinding.driverNumber)
                }
                "Guanyu" -> {
                    Glide.with(context).load(mF1Driver.zhou).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.alfaLogoURL).into(mBinding.driverNumber)
                }
                "Alexander" -> {
                    Glide.with(context).load(mF1Driver.alexAlbon).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.williamsLogoURL).into(mBinding.driverNumber)
                }
                "Lance" -> {
                    Glide.with(context).load(mF1Driver.lanceStroll).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.astonMartinLogoURL).into(mBinding.driverNumber)
                }
                "Mick" -> {
                    Glide.with(context).load(mF1Driver.mickSchumacher).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.haasLogoURL).into(mBinding.driverNumber)
                }
                "Nico" -> {
                    Glide.with(context).load(mF1Driver.nikoHulkenberg).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.astonMartinLogoURL).into(mBinding.driverNumber)

                }
                "Latifi" -> {
                    Glide.with(context).load(mF1Driver.goat).into(mBinding.driverImage)
                    Glide.with(context).load(mF1Team.williamsLogoURL).into(mBinding.driverNumber)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        var mBinding = DriverRowHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(mBinding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(list,position,context = mContext)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}