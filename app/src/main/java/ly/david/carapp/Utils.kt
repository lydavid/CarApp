package ly.david.carapp

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

internal fun Fragment.dialNumber(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    startActivity(intent)
}

internal fun View.loadImageInto(imageUrl: String?, imageView: ImageView) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .error(R.drawable.error_icon)
        .into(imageView)
}
