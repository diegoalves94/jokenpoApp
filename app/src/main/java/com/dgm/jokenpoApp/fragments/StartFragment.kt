package com.dgm.jokenpoApp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dgm.jokenpoApp.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("LifeCycle", "onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("LifeCycle", "onCreateView")
        binding = FragmentStartBinding.inflate(inflater, container, false)
        startPlay()

        return binding.root
    }

    private fun startPlay() {
        binding.btnStart.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToGameNav()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("LifeCycle", "onDetach")
    }
}