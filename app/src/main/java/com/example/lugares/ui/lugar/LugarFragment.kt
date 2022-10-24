package com.example.lugares.ui.lugar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lugares.R
//import com.example.lugares.databinding.FragmentLugarBinding
import com.example.lugares.databinding.FragmentLugarBinding
import com.example.lugares.viewmodel.LugarViewModel

class LugarFragment : Fragment() {
    private var _binding: FragmentLugarBinding? = null
    private val binding get() = _binding!!
    private  lateinit var  LugarViewModel :LugarViewModel

 override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     LugarViewModel =
            ViewModelProvider(this)[LugarViewModel::class.java]
             _binding = FragmentLugarBinding.inflate(inflater, container, false)

     binding.fbAgregar.setOnClickListener(){
         findNavController().navigate(R.id.action_nav_lugar_to_addLugarFragment) }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}