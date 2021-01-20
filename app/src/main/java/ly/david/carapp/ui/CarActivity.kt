package ly.david.carapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ly.david.carapp.R

internal class CarActivity : AppCompatActivity(), ActivityCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.car_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CarListFragment())
                .commitNow()
        }
    }

    override fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

internal interface ActivityCallback {
    fun startFragment(fragment: Fragment)
}
