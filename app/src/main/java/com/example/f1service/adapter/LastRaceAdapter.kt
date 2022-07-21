package com.example.f1service.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1service.F1.F1Cars
import com.example.f1service.F1.F1Driver
import com.example.f1service.F1.F1DriverNumber
import com.example.f1service.F1.F1Team
import com.example.f1service.R
import com.example.f1service.databinding.LastRaceRowHolderBinding
import com.example.f1service.model.DLastRaceResult

class LastRaceAdapter(val raceResults:ArrayList<DLastRaceResult>,var context: Context):RecyclerView.Adapter<LastRaceAdapter.RowHolder> () {

    class RowHolder(val mBinding:LastRaceRowHolderBinding):RecyclerView.ViewHolder(mBinding.root) {

        private val mF1Team = F1Team()
        private val mF1Driver = F1Driver()
        private val mF1Car = F1Cars()

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
                "red_bull" -> {
                    Glide.with(context).load(mF1Car.redBullCarURL).into(mBinding.carImageView)
                    Glide.with(context).load(mF1Team.redBullLogoURL).into(mBinding.teamIcon)
                }
                "mercedes" ->{
                    Glide.with(context).load(mF1Team.mercedesLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.mercedesCarURL).into(mBinding.carImageView)

                }
                "ferrari" -> {
                    Glide.with(context).load(mF1Team.ferrariLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.ferrariCarURL).into(mBinding.carImageView)

                }
                "alphatauri" ->{
                    Glide.with(context).load(mF1Team.alphaTauriLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.alphaTauriCarURL).into(mBinding.carImageView)

                }
                "alfa" ->{
                    Glide.with(context).load(mF1Team.alfaLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.alfaRomeoCarURL).into(mBinding.carImageView)

                }
                "williams" ->{
                    Glide.with(context).load(mF1Team.williamsLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.williamsCarURL).into(mBinding.carImageView)

                }
                "aston_martin" ->{
                    Glide.with(context).load(mF1Team.astonMartinLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.astonMartinCarURL).into(mBinding.carImageView)

                }
                "haas" ->{
                    Glide.with(context).load(mF1Team.haasLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.haasCarURL).into(mBinding.carImageView)

                }
                "mclaren" ->{
                    Glide.with(context).load(mF1Team.mcLarenLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.mcLarenCarURL).into(mBinding.carImageView)

                }
                "alpine" ->{
                    Glide.with(context).load(mF1Team.alphineLogoURL).into(mBinding.teamIcon)
                    Glide.with(context).load(mF1Car.alpineCarURL).into(mBinding.carImageView)

                }
            }

            when(result[position].driverId){
                "max_verstappen" -> Glide.with(context).load(mF1Driver.maxVerstappen).into(mBinding.pilotImageView)
                "perez" -> Glide.with(context).load(mF1Driver.sergioPerez).into(mBinding.pilotImageView)
                "russell" -> Glide.with(context).load(mF1Driver.georgeRussell).into(mBinding.pilotImageView)
                "hamilton" -> Glide.with(context).load(mF1Driver.lewisHamilton).into(mBinding.pilotImageView)
                "gasly" -> Glide.with(context).load(mF1Driver.pierreGasly).into(mBinding.pilotImageView)
                "vettel" -> Glide.with(context).load(mF1Driver.sebastianVettel).into(mBinding.pilotImageView)
                "alonso" -> Glide.with(context).load(mF1Driver.fernandoAlanso).into(mBinding.pilotImageView)
                "ricciardo" -> Glide.with(context).load(mF1Driver.danielRiccardo).into(mBinding.pilotImageView)
                "norris" -> Glide.with(context).load(mF1Driver.landoNorris).into(mBinding.pilotImageView)
                "ocon" -> Glide.with(context).load(mF1Driver.estebanOcon).into(mBinding.pilotImageView)
                "bottas" -> Glide.with(context).load(mF1Driver.valtteriBottas).into(mBinding.pilotImageView)
                "albon" -> Glide.with(context).load(mF1Driver.alexAlbon).into(mBinding.pilotImageView)
                "tsunoda" -> Glide.with(context).load(mF1Driver.yukiTsunoda).into(mBinding.pilotImageView)
                "mick_schumacher" -> Glide.with(context).load(mF1Driver.mickSchumacher).into(mBinding.pilotImageView)
                "latifi" -> Glide.with(context).load(mF1Driver.goat).into(mBinding.pilotImageView)
                "stroll" -> Glide.with(context).load(mF1Driver.lanceStroll).into(mBinding.pilotImageView)
                "kevin_magnussen" -> Glide.with(context).load(mF1Driver.kevinMagnussen).into(mBinding.pilotImageView)
                "zhou"->Glide.with(context).load(mF1Driver.zhou).into(mBinding.pilotImageView)
                "leclerc" -> Glide.with(context).load(mF1Driver.charlesLeclerc).into(mBinding.pilotImageView)
                "sainz" -> Glide.with(context).load(mF1Driver.carlosSainz).into(mBinding.pilotImageView)
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