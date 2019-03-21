package com.github.alexdochioiu.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
@Module
internal class NetworkModule {

    @NetworkScope
    @Provides
    internal fun okHttpClient() : OkHttpClient =
        OkHttpClient
            .Builder()
            .build()
}