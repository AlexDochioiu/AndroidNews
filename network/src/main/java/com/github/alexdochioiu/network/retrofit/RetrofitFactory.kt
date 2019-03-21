package com.github.alexdochioiu.network.retrofit

import com.github.alexdochioiu.network.NetworkScope
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
@NetworkScope
class RetrofitFactory @Inject internal constructor(private val okHttpClient: OkHttpClient){

    fun makeInstance(baseUrl: String) : Retrofit =
            Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build()
}