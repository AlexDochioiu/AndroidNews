package com.github.alexdochioiu.network

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 */
@Module
internal class NetworkModule {

    @Provides
    @NetworkScope
    internal fun loggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.v("okhttp3", message) }
            .apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    @NetworkScope
    internal fun cache(cacheFile: File): Cache = Cache(cacheFile, 10 * 1000 * 1000) //10MB Cache

    @Provides
    @NetworkScope
    internal fun cacheFile(context: Context): File = File(context.cacheDir, "okhttp_cache")

    @NetworkScope
    @Provides
    internal fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
}