package com.dispositivosmoviles.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dispositivosmoviles.R
import com.dispositivosmoviles.databinding.FragmentUpdateLugarBinding
import com.dispositivosmoviles.model.Lugar
import com.dispositivosmoviles.viewmodel.HomeViewModel


class UpdateLugarFragment : Fragment() {

    //recuperaargumentos

    private val args by navArgs<UpdateLugarFragmentArgs>()

    private var _binding: FragmentUpdateLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel ::class.java)
        _binding = FragmentUpdateLugarBinding.inflate(inflater,container,false)

        //cargar valores edit

        binding.etNombre.setText(args.lugar.nombre)
        binding.etTelefono.setText(args.lugar.telefono)
        binding.etCorreoLugar.setText(args.lugar.correo)
        binding.etWeb.setText(args.lugar.web)

        binding.btUpdateLugar.setOnClickListener{updateLugar()}


        return binding.root
    }


        private  fun updateLugar(){
            val nombre = binding.etNombre.text.toString()
            val correo = binding.etCorreoLugar.text.toString()
            val telefono = binding.etTelefono.text.toString()
            val web = binding.etWeb.text.toString()

            if(nombre.isEmpty()){
                Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG)


            }else if(correo.isEmpty()){
                Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG)
            }else{
                val lugar = Lugar( args.lugar.id,nombre,correo,web,telefono)
                homeViewModel.saveLugar(lugar)
                Toast.makeText(requireContext(),getString(R.string.msg_lugar_added),Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateLugarFragment_to_nav_home)

            }
        }

    private fun deleteLugar() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.bt_delete_lugar))
        builder.setMessage(getString(R.string.msg_seguro_borrado)+" ${args.lugar.nombre}?")
        builder.setNegativeButton(getString(R.string.msg_no)) {_,_ -> }
        builder.setPositiveButton(getString(R.string.msg_si)) {_,_ ->
            homeViewModel.deleteLugar(args.lugar)
            Toast.makeText(requireContext(),
                getString(R.string.msg_lugar_deleted),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateLugarFragment_to_nav_home)
        }

        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}