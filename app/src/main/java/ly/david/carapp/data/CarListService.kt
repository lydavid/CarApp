package ly.david.carapp.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://carfax-for-consumers.firebaseio.com/"

internal interface CarListService {

    @GET("assignment.json")
    suspend fun getCarList(): CarList

    companion object {
        fun create(): CarListService = Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .baseUrl(BASE_URL)
            .build()
            .create(CarListService::class.java)
    }
}

