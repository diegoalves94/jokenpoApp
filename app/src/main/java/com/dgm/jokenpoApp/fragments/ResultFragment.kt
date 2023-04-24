package com.dgm.jokenpoApp.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.dgm.jokenpoApp.R
import com.dgm.jokenpoApp.databinding.FragmentResultBinding
import com.dgm.jokenpoApp.databinding.FragmentStartBinding

class ResultFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.screen_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return menuItem.onNavDestinationSelected(findNavController())
    }
}