package com.github.alexdochioiu.network

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File


/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
@Module
internal class NetworkModule {

    @Provides
    @NetworkScope
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Timber.i("NETWORK: $message") }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @NetworkScope
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10MB Cache
    }

    @Provides
    @NetworkScope
    fun cacheFile(context: Context): File {
        return File(context.getCacheDir(), "okhttp_cache")
    }

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