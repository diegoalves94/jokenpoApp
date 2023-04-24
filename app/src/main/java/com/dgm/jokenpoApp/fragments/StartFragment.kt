package com.dgm.jokenpoApp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dgm.jokenpoApp.databinding.FragmentStartBinding
import com.dgm.jokenpoApp.observers.CustomObserver

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        startPlay()

        lifecycle.addObserver(CustomObserver())

        return binding.root
    }

    private fun startPlay() {
        binding.btnStart.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToGameNav()
            findNavController().navigate(action)
        }
    }

}