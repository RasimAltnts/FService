package com.example.f1service.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1service.F1.F1DriverNumber
import com.example.f1service.F1.F1Team
import com.example.f1service.R
import com.example.f1service.databinding.LastRaceRowHolderBinding
import com.example.f1service.model.DLastRaceResult

class LastRaceAdapter(val raceResults:ArrayList<DLastRaceResult>,var context: Context):RecyclerView.Adapter<LastRaceAdapter.RowHolder> () {

    class RowHolder(val mBinding:LastRaceRowHolderBinding):RecyclerView.ViewHolder(mBinding.root) {

        val mF1Team = F1Team()
        val mF1Driver = F1DriverNumber()

        @SuppressLint("SetTextI18n")
        fun bind(result:ArrayList<DLastRaceResult>, position: Int,context:Context) {
            mBinding.position.text = result[position].position
            if (result[position].fastestLap == "1") {
                mBinding.pilotName.text = "${result[position].pilotName} ${result[position].pilotSurname} *"
            }
            else {
                mBinding.pilotName.text = "${result[position].pilotName} ${result[position].pilotSurname}"
            }

            mBinding.points.text = "+${result[position].point} P"


            when(result[position].constructorId) {
                "red_bull" -> Glide.with(context).load(mF1Team.redBullLogoURL).into(mBinding.teamIcon)
                "mercedes" -> Glide.with(context).load(mF1Team.mercedesLogoURL).into(mBinding.teamIcon)
                "ferrari" -> Glide.with(context).load(mF1Team.ferrariLogoURL).into(mBinding.teamIcon)
                "alphatauri" -> Glide.with(context).load(mF1Team.alphaTauriLogoURL).into(mBinding.teamIcon)
                "alfa" -> Glide.with(context).load(mF1Team.alfaLogoURL).into(mBinding.teamIcon)
                "williams" -> Glide.with(context).load(mF1Team.williamsLogoURL).into(mBinding.teamIcon)
                "aston_martin" -> Glide.with(context).load(mF1Team.astonMartinLogoURL).into(mBinding.teamIcon)
                "haas" -> Glide.with(context).load(mF1Team.haasLogoURL).into(mBinding.teamIcon)
                "mclaren" -> Glide.with(context).load(mF1Team.mcLarenLogoURL).into(mBinding.teamIcon)
                "alpine" -> Glide.with(context).load(mF1Team.alphineLogoURL).into(mBinding.teamIcon)
            }

            when(result[position].driverId){
                "max_verstappen" -> Glide.with(context).load(mF1Driver.maxURL).into(mBinding.pilotNumber)
                "perez" -> Glide.with(context).load(mF1Driver.sergioURL).into(mBinding.pilotNumber)
                "russell" -> Glide.with(context).load(mF1Driver.georgeURL).into(mBinding.pilotNumber)
                "hamilton" -> Glide.with(context).load(mF1Driver.lewisURL).into(mBinding.pilotNumber)
                "gasly" -> Glide.with(context).load(mF1Driver.pierreURL).into(mBinding.pilotNumber)
                "vettel" -> Glide.with(context).load(mF1Driver.vettelURL).into(mBinding.pilotNumber)
                "alonso" -> Glide.with(context).load(mF1Driver.alonsoURL).into(mBinding.pilotNumber)
                "ricciardo" -> Glide.with(context).load(mF1Driver.danielURL).into(mBinding.pilotNumber)
                "norris" -> Glide.with(context).load(mF1Driver.norrisURL).into(mBinding.pilotNumber)
                "ocon" -> Glide.with(context).load(mF1Driver.oconURL).into(mBinding.pilotNumber)
                "bottas" -> Glide.with(context).load(mF1Driver.bottasURL).into(mBinding.pilotNumber)
                "albon" -> Glide.with(context).load(mF1Driver.alexAlbonURL).into(mBinding.pilotNumber)
                "tsunoda" -> Glide.with(context).load(mF1Driver.yukiURL).into(mBinding.pilotNumber)
                "mick_schumacher" -> Glide.with(context).load(mF1Driver.mickURL).into(mBinding.pilotNumber)
                "latifi" -> Glide.with(context).load(mF1Driver.goat).into(mBinding.pilotNumber)
                "stroll" -> Glide.with(context).load(mF1Driver.lanceURL).into(mBinding.pilotNumber)
                "kevin_magnussen" -> Glide.with(context).load(mF1Driver.magnussenURL).into(mBinding.pilotNumber)
                "zhou"->Glide.with(context).load(mF1Driver.zhouURL).into(mBinding.pilotNumber)
                "leclerc" -> Glide.with(context).load(mF1Driver.charlesURL).into(mBinding.pilotNumber)
                "sainz" -> Glide.with(context).load(mF1Driver.sainzURL).into(mBinding.pilotNumber)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val mBinding = LastRaceRowHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(mBinding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(raceResults,position,context)
    }

    override fun getItemCount(): Int {
        return raceResults.size
    }
}