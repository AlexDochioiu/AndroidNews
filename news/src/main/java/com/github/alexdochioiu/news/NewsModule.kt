package com.github.alexdochioiu.news

import com.github.alexdochioiu.network.retrofit.RetrofitFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 */
@Module
internal class NewsModule {

    @Provides
    @NewsScope
    internal fun retrofit(retrofitFactory: RetrofitFactory): Retrofit = retrofitFactory.makeInstance(baseNewsApiUrl)
}