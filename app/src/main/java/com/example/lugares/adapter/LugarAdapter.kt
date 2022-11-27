package com.example.lugares.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.dispositivosmoviles.databinding.FragmentLugarFilaBinding
import com.dispositivosmoviles.model.Lugar
import com.dispositivosmoviles.ui.home.HomeFragmentDirections
import com.dispositivosmoviles.ui.home.UpdateLugarFragmentDirections

class LugarAdapter: RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {


    private var listaLugares = emptyList<Lugar>()

    inner class LugarViewHolder(private val itemBinding: FragmentLugarFilaBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun dibujar(lugar: Lugar) {
            itemBinding.tvNombre.text = lugar.nombre
            itemBinding.tvTelefono.text = lugar.telefono
            itemBinding.tvCorreo.text = lugar.correo

            //evento edit

            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections
                    .actionNavHomeToUpdateLugarFragment(lugar)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    fun setLugares(lugares : List<Lugar>){
        listaLugares = lugares
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding = FragmentLugarFilaBinding
            .inflate(LayoutInflater.from(parent.context)
            ,parent,false)
        return LugarViewHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
       val lugar = listaLugares[position]
        holder.dibujar(lugar)
    }


    override fun getItemCount(): Int {
        return listaLugares.size
    }
}