package es.jfechevarria.app_base.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import es.jfechevarria.app_base.alias.Inflate

abstract class AbstractFragment<VB: ViewBinding>(private val inflate: Inflate<VB>): Fragment() {

    lateinit var binding: VB

    companion object {
        const val BUNDLE_ARGS = "bundle_nav_args_state_handle"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun setArguments(args: Bundle?) {
        if (args != null) {
            if (args.getBundle(BUNDLE_ARGS) != null) {
                super.setArguments(args)
            } else {
                super.setArguments(Bundle(args).apply {
                    putBundle(BUNDLE_ARGS, args) // Wrap the arguments as BUNDLE_ARGS
                })
            }
        } else {
            super.setArguments(null)
        }
    }
}