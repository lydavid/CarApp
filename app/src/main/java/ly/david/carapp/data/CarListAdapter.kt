package ly.david.carapp.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ly.david.carapp.R
import ly.david.carapp.databinding.CarListingBinding

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
            Glide.with(binding.root)
                .load(listing.images.firstImage?.largeImage)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(R.drawable.error_icon)
                .into(binding.image)
            binding.carListing.setOnClickListener {
                onListingClickListener(listing)
            }
            binding.callDealerButton.setOnClickListener {
                onCallDealerClickListener(listing.dealer.phone)
            }
            binding.title.text = listing.getFormattedTitle()
            binding.subtitle.text = listing.getFormattedSubtitle()
            binding.location.text = listing.dealer.getFormattedLocation()
        }
    }
}

