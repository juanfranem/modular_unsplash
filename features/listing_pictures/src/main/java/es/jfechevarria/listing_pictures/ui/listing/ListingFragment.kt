package es.jfechevarria.listing_pictures.ui.listing

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import es.jfechevarria.app_base.fragments.AbstractFragment
import es.jfechevarria.listing_pictures.common.adapters.PicturePagerAdapter
import es.jfechevarria.listing_pictures.databinding.FragmentListingBinding
import es.jfechevarria.listing_pictures.dii.loadListingModule
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListingFragment: AbstractFragment<FragmentListingBinding>(FragmentListingBinding::inflate) {

    private lateinit var picturePagerAdapter: PicturePagerAdapter
    private val viewModel by sharedViewModel<ListingViewModel>()
    private fun inject() = loadListingModule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        picturePagerAdapter = PicturePagerAdapter(requireActivity())
        binding.viewPager.adapter = picturePagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager
        ) { tab, position ->
            tab.icon = picturePagerAdapter.getItemIcon(position)
            tab.text = picturePagerAdapter.getItemName(position)
        }.attach()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.orderByList().observe(viewLifecycleOwner) {
                picturePagerAdapter.submitList(it)
            }
            viewModel.viewPagerSelected.observe(viewLifecycleOwner) {
                Handler(Looper.getMainLooper()).post {
                    binding.viewPager.currentItem = it
                }
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.updatePageSelected(position)
            }
        })
    }

}