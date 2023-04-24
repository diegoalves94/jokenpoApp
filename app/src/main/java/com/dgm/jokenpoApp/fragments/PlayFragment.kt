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
import com.dgm.jokenpoApp.databinding.FragmentPlayBinding
import com.dgm.jokenpoApp.databinding.FragmentResultBinding


class PlayFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayBinding.inflate(inflater, container, false)

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