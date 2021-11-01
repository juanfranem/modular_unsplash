package es.jfechevarria.vivelibre.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import es.jfechevarria.app_base.fragments.AbstractFragment
import es.jfechevarria.vivelibre.R
import es.jfechevarria.vivelibre.databinding.FragmentHomeBinding

class HomeFragment: AbstractFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().navigate(
            R.id.from_homeFragment_to_listingNav
        )
    }
}