package com.github.alexdochioiu.news

import com.github.alexdochioiu.network.retrofit.RetrofitFactory
import com.github.alexdochioiu.news.retrofit.NewsService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 */
@Module
internal class NewsModule {

    @Provides
    @NewsScope
    internal fun newsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Provides
    @NewsScope
    internal fun retrofit(
        retrofitFactory: RetrofitFactory,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit = retrofitFactory.makeInstance(baseNewsApiUrl, converterFactory, callAdapterFactory)

    @Provides
    @NewsScope
    internal fun converterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @NewsScope
    internal fun callAdapterFactory(): CallAdapter.Factory = CoroutineCallAdapterFactory()

    @Provides
    @NewsScope
    internal fun moshi(): Moshi = Moshi.Builder().build()
}