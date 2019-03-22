package com.github.alexdochioiu.network.retrofit

import com.github.alexdochioiu.network.NetworkScope
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
@NetworkScope
class RetrofitFactory @Inject internal constructor(private val okHttpClient: OkHttpClient){

    fun makeInstance(
        baseUrl: String,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ) : Retrofit =
            Retrofit
                .Builder()
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .baseUrl(baseUrl)
                .build()
}