package ly.david.carapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ly.david.carapp.data.CarListService
import ly.david.carapp.data.Listing

internal class CarListViewModel : ViewModel() {

    private val carListService by lazy {
        CarListService.create()
    }

    // TODO: Cache this locally as well
    internal val carListings = MutableLiveData<List<Listing>>()

    init {
        viewModelScope.launch {
            carListings.postValue(carListService.getCarList().listings)
        }
    }
}
