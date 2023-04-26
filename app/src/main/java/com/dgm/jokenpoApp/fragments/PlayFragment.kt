package com.dgm.jokenpoApp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.dgm.jokenpoApp.GameNavDirections
import com.dgm.jokenpoApp.R
import com.dgm.jokenpoApp.business.PlayerListener
import com.dgm.jokenpoApp.databinding.FragmentPlayBinding


class PlayFragment : Fragment(), MenuProvider, AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentPlayBinding
    private lateinit var spinnerPlay: Spinner
    private lateinit var listener: PlayerListener

    private var selectedPLay: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("LifeCycle", "onAttach")

        try {
            listener = context as PlayerListener
        } catch (e: Error) {
            Log.d("ListenerError", e.message.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("LifeCycle", "onCreateView")
        binding = FragmentPlayBinding.inflate(inflater, container, false)

        initMenu()
        setupPLay(savedInstanceState)

        return binding.root
    }

    private fun initMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupPLay(savedInstanceState: Bundle?) {
        spinnerPlay = binding.spnSelectPlay

        val adapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.plays,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerPlay.adapter = adapter
        spinnerPlay.onItemSelectedListener = this

        savedInstanceState?.getInt("selectedPlay")?.let {
            spinnerPlay.setSelection(it)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val plays = resources.getStringArray(R.array.plays)
        listener.onPlaySelected(plays[position])
        selectedPLay = position
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selectedPlay", selectedPLay)
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