package ly.david.carapp

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

internal fun Fragment.dialNumber(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    startActivity(intent)
}
