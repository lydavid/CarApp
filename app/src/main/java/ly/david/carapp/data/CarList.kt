package ly.david.carapp.data

import com.squareup.moshi.Json
import java.io.Serializable

internal data class CarList(
    val listings: List<Listing>
)

internal data class Listing(
    val dealer: Dealer,
    val mileage: Int,
    val currentPrice: Int,
    val exteriorColor: String,
    val interiorColor: String,
    val engine: String,
    val driveType: String = "",
    val transmission: String,
    val bodyType: String = "",
    val images: Images,
    val year: Int,
    val make: String,
    val model: String,
    val trim: String
) : Serializable

internal data class Dealer(
    val phone: String,
    val city: String,
    val state: String
) : Serializable

internal data class Images(
    @Json(name = "firstPhoto")
    val firstImage: FirstImage? = null
) : Serializable

internal data class FirstImage(
    @Json(name = "large")
    val largeImage: String
) : Serializable

internal fun Listing.getFormattedTitle() = "$year $model $make $trim"

internal fun Listing.getFormattedSubtitle() = "$ $currentPrice | $mileage mi"

internal fun Dealer.getFormattedLocation() = "$city, $state"

internal fun Listing.getFormattedVehicleInfo() =
    """
        Location: ${dealer.getFormattedLocation()}
        Exterior Color: $exteriorColor
        Interior Color: $interiorColor
        Drive Type: $driveType
        Transmission: $transmission
        Body Style: $bodyType
        Engine: $engine
    """.trimIndent()
