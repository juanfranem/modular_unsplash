package es.jfechevarria.listing_pictures.common.adapters

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.jfechevarria.domain.picture.repositories.domain.PictureOrderBy
import es.jfechevarria.vivelibre.R
import es.jfechevarria.listing_pictures.common.diffUtils.PictureOrderByDiffUtil
import es.jfechevarria.listing_pictures.ui.listingPage.ListingPageFragment

class PicturePagerAdapter(
    private val activity: FragmentActivity
    ): FragmentStateAdapter(activity.supportFragmentManager, activity.lifecycle) {

    private val picturesTypes = mutableListOf<PictureOrderBy>()

    fun submitList(list: List<PictureOrderBy>) {
        val diffResult = DiffUtil.calculateDiff(PictureOrderByDiffUtil(picturesTypes, list))
        picturesTypes.clear()
        picturesTypes.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItemName(position: Int): String {
        return picturesTypes[position].toString()
            .replaceFirstChar { it.uppercase() }
    }

    fun getItemIcon(position: Int): Drawable? {
        val resources = activity.resources
        val theme = activity.theme
        return when (picturesTypes[position]) {
            PictureOrderBy.OLDEST -> ResourcesCompat.getDrawable(resources, R.drawable.ic_oldest, theme)
            PictureOrderBy.LATEST -> ResourcesCompat.getDrawable(resources, R.drawable.ic_latest, theme)
            PictureOrderBy.POPULAR -> ResourcesCompat.getDrawable(resources, R.drawable.ic_popular, theme)
            PictureOrderBy.SAVED -> ResourcesCompat.getDrawable(resources, R.drawable.ic_bookmark_saved, theme)
        }
    }

    override fun getItemCount(): Int = picturesTypes.size

    override fun createFragment(position: Int): Fragment {
        return ListingPageFragment.createInstance(picturesTypes[position])
    }
}