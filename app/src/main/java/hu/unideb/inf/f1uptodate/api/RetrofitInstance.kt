package hu.unideb.inf.f1uptodate.api

import retrofit2.Retrofit
import hu.unideb.inf.f1uptodate.utils.Constants.Companion.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: OnlineF1API by lazy{
        retrofit.create(OnlineF1API::class.java)
    }

}