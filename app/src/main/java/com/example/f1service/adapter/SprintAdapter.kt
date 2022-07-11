package com.example.f1service.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1service.F1.F1Cars
import com.example.f1service.F1.F1Driver
import com.example.f1service.F1.F1DriverNumber
import com.example.f1service.F1.F1Team
import com.example.f1service.databinding.SprintResultRowHolderBinding
import com.example.f1service.model.DF1SprintDriverModels
import com.example.f1service.view.F1Drivers

class SprintAdapter(val list:ArrayList<DF1SprintDriverModels>,val context:Context):RecyclerView.Adapter<SprintAdapter.RowHolder> () {

    class RowHolder(val bind:SprintResultRowHolderBinding):RecyclerView.ViewHolder(bind.root) {

        private val mF1Team = F1Team()
        private val mF1Driver = F1Driver()
        private val mF1Car = F1Cars()
        @SuppressLint("SetTextI18n")
        fun bind(list:ArrayList<DF1SprintDriverModels>, position: Int,context: Context){
            bind.pilotName.text = "${list[position].name} ${list[position].familyName}"
            bind.position.text = list[position].position
            bind.points.text = "+${list[position].points} P"


            when(list[position].team) {
                "Red Bull" -> {
                    Glide.with(context).load(mF1Car.redBullCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.redBullLogoURL).into(bind.teamImageView)
                }

                "Ferrari" -> {
                    Glide.with(context).load(mF1Car.ferrariCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.ferrariLogoURL).into(bind.teamImageView)
                }

                "Mercedes" -> {
                    Glide.with(context).load(mF1Car.mercedesCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.mercedesLogoURL).into(bind.teamImageView)
                }

                "McLaren" -> {
                    Glide.with(context).load(mF1Car.mcLarenCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.mcLarenLogoURL).into(bind.teamImageView)
                }

                "Alpine F1 Team" -> {
                    Glide.with(context).load(mF1Car.alpineCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.alphineLogoURL).into(bind.teamImageView)
                }

                "Alfa Romeo" -> {
                    Glide.with(context).load(mF1Car.alfaRomeoCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.alfaLogoURL).into(bind.teamImageView)
                }
                "AlphaTauri" -> {
                    Glide.with(context).load(mF1Car.alphaTauriCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.alphaTauriLogoURL).into(bind.teamImageView)
                }
                "Aston Martin" -> {
                    Glide.with(context).load(mF1Car.astonMartinCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.astonMartinLogoURL).into(bind.teamImageView)
                }
                "Haas F1 Team" -> {
                    Glide.with(context).load(mF1Car.haasCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.haasLogoURL).into(bind.teamImageView)
                }
                "Williams" -> {
                    Glide.with(context).load(mF1Car.williamsCarURL).into(bind.carImageView)
                    Glide.with(context).load(mF1Team.williamsLogoURL).into(bind.teamImageView)
                }
            }

            when(list[position].driverId){
                "max_verstappen" -> Glide.with(context).load(mF1Driver.maxVerstappen).into(bind.pilotImageView)
                "perez" -> Glide.with(context).load(mF1Driver.sergioPerez).into(bind.pilotImageView)
                "russell" -> Glide.with(context).load(mF1Driver.georgeRussell).into(bind.pilotImageView)
                "hamilton" -> Glide.with(context).load(mF1Driver.lewisHamilton).into(bind.pilotImageView)
                "gasly" -> Glide.with(context).load(mF1Driver.pierreGasly).into(bind.pilotImageView)
                "vettel" -> Glide.with(context).load(mF1Driver.sebastianVettel).into(bind.pilotImageView)
                "alonso" -> Glide.with(context).load(mF1Driver.fernandoAlanso).into(bind.pilotImageView)
                "ricciardo" -> Glide.with(context).load(mF1Driver.danielRiccardo).into(bind.pilotImageView)
                "norris" -> Glide.with(context).load(mF1Driver.landoNorris).into(bind.pilotImageView)
                "ocon" -> Glide.with(context).load(mF1Driver.estebanOcon).into(bind.pilotImageView)
                "bottas" -> Glide.with(context).load(mF1Driver.valtteriBottas).into(bind.pilotImageView)
                "albon" -> Glide.with(context).load(mF1Driver.alexAlbon).into(bind.pilotImageView)
                "tsunoda" -> Glide.with(context).load(mF1Driver.yukiTsunoda).into(bind.pilotImageView)
                "mick_schumacher" -> Glide.with(context).load(mF1Driver.mickSchumacher).into(bind.pilotImageView)
                "latifi" -> Glide.with(context).load(mF1Driver.goat).into(bind.pilotImageView)
                "stroll" -> Glide.with(context).load(mF1Driver.lanceStroll).into(bind.pilotImageView)
                "kevin_magnussen" -> Glide.with(context).load(mF1Driver.kevinMagnussen).into(bind.pilotImageView)
                "zhou"->Glide.with(context).load(mF1Driver.zhou).into(bind.pilotImageView)
                "leclerc" -> Glide.with(context).load(mF1Driver.charlesLeclerc).into(bind.pilotImageView)
                "sainz" -> Glide.with(context).load(mF1Driver.carlosSainz).into(bind.pilotImageView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val bind = SprintResultRowHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return RowHolder(bind)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(list, position,context)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}