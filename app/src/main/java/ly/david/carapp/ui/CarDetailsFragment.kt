package ly.david.carapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ly.david.carapp.data.*
import ly.david.carapp.databinding.CarDetailsFragmentBinding
import ly.david.carapp.dialNumber
import ly.david.carapp.loadImageInto

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
        with(binding) {
            root.loadImageInto(listing.images.firstImage?.largeImage, binding.image)
            callDealerButton.setOnClickListener { dialNumber(listing.dealer.phone) }
            title.text = listing.getFormattedTitle()
            subtitle.text = listing.getFormattedSubtitle()
            location.text = listing.dealer.getFormattedLocation()
            vehicleInfo.text = listing.getFormattedVehicleInfo()
        }
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
