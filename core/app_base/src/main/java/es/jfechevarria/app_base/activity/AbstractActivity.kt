package es.jfechevarria.app_base.activity

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import es.jfechevarria.app_base.alias.Inflate

abstract class AbstractActivity<VB: ViewBinding>(private val inflater: Inflate<VB>): FragmentActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflater.invoke(layoutInflater, null, false)
        setContentView(binding.root)
    }


}