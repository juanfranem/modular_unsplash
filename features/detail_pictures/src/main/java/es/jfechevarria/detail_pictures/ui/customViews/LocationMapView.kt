package es.jfechevarria.detail_pictures.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import es.jfechevarria.detail_pictures.databinding.LocationMapViewBinding
import es.jfechevarria.domain.pictureLocation.LocationPicture
import es.jfechevarria.vivelibre.BuildConfig
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class LocationMapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: LocationMapViewBinding

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = LocationMapViewBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)
        binding.root.isVisible = false
        binding.mapView.isVisible = false
        binding.locationText.isVisible = false
        binding.mapView.setOnTouchListener { view, motionEvent ->
            true
        }
        val provider = Configuration.getInstance()
        provider.userAgentValue = BuildConfig.APPLICATION_ID
        binding.mapView.setTileSource(TileSourceFactory.MAPNIK)
        binding.mapView.setMultiTouchControls(false)

    }

    fun setLocation(location: LocationPicture?) {
        location?.position?.let {
            val mapController =  binding.mapView.controller
            mapController.setZoom(5.0)
            val locationPoint = GeoPoint(it.value.first, it.value.second)
            mapController.setCenter(locationPoint)
            val marker = Marker(binding.mapView)
            marker.position = locationPoint
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            binding.mapView.overlays.add(marker)
            binding.mapView.isVisible = true
            binding.mapView.invalidate()
        }

        location?.let {
            if (!it.city?.value.isNullOrEmpty() && !it.country?.value.isNullOrEmpty()) {
                binding.root.isVisible = true
                binding.locationText.isVisible = true
                binding.locationText.text = "${it.city?.value}, ${it.country?.value}"
            }
        }

    }
}