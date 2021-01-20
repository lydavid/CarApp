package ly.david.carapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ly.david.carapp.data.CarListAdapter
import ly.david.carapp.data.Listing
import ly.david.carapp.databinding.CarListFragmentBinding
import ly.david.carapp.dialNumber

internal class CarListFragment : Fragment() {

    private lateinit var binding: CarListFragmentBinding

    private val viewModel: CarListViewModel by viewModels()

    private val adapter by lazy {
        CarListAdapter(::navigateToDetails, ::dialNumber)
    }

    private var activityCallback: ActivityCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityCallback = context as ActivityCallback
    }

    override fun onDetach() {
        super.onDetach()
        activityCallback = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CarListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.carListRecyclerView.adapter = adapter
        viewModel.carListings.observe(viewLifecycleOwner, { adapter.setListings(it) })
    }

    private fun navigateToDetails(listing: Listing) {
        activityCallback?.startFragment(CarDetailsFragment.newInstance(listing))
    }
}
