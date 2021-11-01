package es.jfechevarria.listing_pictures.ui.listingPage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import es.jfechevarria.app_base.fragments.AbstractFragment
import es.jfechevarria.domain.picture.repositories.domain.PictureOrderBy
import es.jfechevarria.listing_pictures.common.adapters.PictureAdapter
import es.jfechevarria.listing_pictures.databinding.FragmentListingPageBinding
import es.jfechevarria.listing_pictures.ui.listing.ListingFragmentDirections
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListingPageFragment: AbstractFragment<FragmentListingPageBinding>(FragmentListingPageBinding::inflate) {

    private val viewModel: ListingPageViewModel by viewModel()
    private lateinit var adapter: PictureAdapter

    companion object {
        private const val ORDER_BY_ITEM = "ORDER_BY_ITEM"
        fun createInstance(orderBy: PictureOrderBy): ListingPageFragment {
            return ListingPageFragment().apply {
                arguments = Bundle().apply { putString(ORDER_BY_ITEM, orderBy.name) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PictureAdapter {
            findNavController().navigate(
                ListingFragmentDirections.listingToDetail(it.id.value)
            )
        }
        binding.recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPicturesPagingFlow(
                PictureOrderBy.valueOf(arguments?.getString(ORDER_BY_ITEM).orEmpty())
            ).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}