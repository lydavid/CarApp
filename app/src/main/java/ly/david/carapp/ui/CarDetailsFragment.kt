package ly.david.carapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import ly.david.carapp.R
import ly.david.carapp.data.*
import ly.david.carapp.databinding.CarDetailsFragmentBinding
import ly.david.carapp.dialNumber

private const val LISTING_KEY = "listing"

internal class CarDetailsFragment : Fragment() {

    private lateinit var binding: CarDetailsFragmentBinding
    private lateinit var listing: Listing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run { listing = this.getSerializable(LISTING_KEY) as Listing }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CarDetailsFragmentBinding.inflate(inflater, container, false)
        // TODO: Extract as ext function
        Glide.with(binding.root)
            .load(listing.images.firstImage?.largeImage)
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(R.drawable.error_icon)
            .into(binding.image)
        binding.callDealerButton.setOnClickListener { dialNumber(listing.dealer.phone) }
        binding.title.text = listing.getFormattedTitle()
        binding.subtitle.text = listing.getFormattedSubtitle()
        binding.location.text = listing.dealer.getFormattedLocation()
        binding.vehicleInfo.text = listing.getFormattedVehicleInfo()
        return binding.root
    }

    companion object {
        fun newInstance(listing: Listing): Fragment =
            CarDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LISTING_KEY, listing)
                }
            }
    }
}
