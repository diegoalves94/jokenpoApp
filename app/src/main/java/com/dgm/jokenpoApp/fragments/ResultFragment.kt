package com.dgm.jokenpoApp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.dgm.jokenpoApp.GameNavDirections
import com.dgm.jokenpoApp.R
import com.dgm.jokenpoApp.business.JokenpoEngine
import com.dgm.jokenpoApp.business.Result
import com.dgm.jokenpoApp.databinding.FragmentResultBinding

class ResultFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentResultBinding
    private lateinit var engine: JokenpoEngine
    private lateinit var resultText: TextView
    private lateinit var playerSelectionText: TextView
    private lateinit var aiSelectionText: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("LifeCycle", "onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("LifeCycle", "onCreateView")
        binding = FragmentResultBinding.inflate(inflater, container, false)

        initMenu()
        setupEngine()

        return binding.root
    }

    private fun initMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupEngine() {
        engine = JokenpoEngine(resources.getStringArray(R.array.plays))

        val currentPlay = arguments?.getString("currentPlay")
        currentPlay?.let {
            updateResultText(it)
        }
    }

    private fun updateResultText(currentPlay: String) {
        resultText = binding.tvWinner
        playerSelectionText = binding.tvPlayerSelection
        aiSelectionText = binding.tvAiSelection

        val resultGame = engine.calculateResult(currentPlay)

        resultText.text = when (resultGame) {
            Result.WIN -> "O jogador 1\nGANHOU!"
            Result.LOSS -> "O jogador 1\nPERDEU!"
            else -> "Você\nEMPATOU!"
        }

        playerSelectionText.text = "Jogador 1 -> $currentPlay"
        aiSelectionText.text = "AI -> ${engine.getAISelectedPlay()}"
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.screen_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        menuItem.setOnMenuItemClickListener() {
            val action = GameNavDirections.actionGlobalStartFragment()
            this.findNavController().navigate(action)
            true
        }
        return false
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