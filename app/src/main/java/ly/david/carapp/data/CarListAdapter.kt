package ly.david.carapp.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ly.david.carapp.databinding.CarListingBinding
import ly.david.carapp.loadImageInto

internal class CarListAdapter(
    private val onListingClickListener: (Listing) -> Unit,
    private val onCallDealerClickListener: (String) -> Unit
) : RecyclerView.Adapter<CarListAdapter.CarListViewHolder>() {

    private var listings: List<Listing> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarListingBinding.inflate(inflater, parent, false)
        return CarListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
        val listing = listings[position]
        holder.bind(listing)
    }

    override fun getItemCount(): Int = listings.size

    fun setListings(listings: List<Listing>) {
        this.listings = listings
        notifyDataSetChanged()
    }

    inner class CarListViewHolder(
        private val binding: CarListingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listing: Listing) {
            with(binding) {
                root.loadImageInto(listing.images.firstImage?.largeImage, binding.image)
                carListing.setOnClickListener { onListingClickListener(listing) }
                callDealerButton.setOnClickListener { onCallDealerClickListener(listing.dealer.phone) }
                title.text = listing.getFormattedTitle()
                subtitle.text = listing.getFormattedSubtitle()
                location.text = listing.dealer.getFormattedLocation()
            }
        }
    }
}

