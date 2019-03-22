package com.github.alexdochioiu.news

import com.github.alexdochioiu.network.NetworkComponent
import com.github.alexdochioiu.news.retrofit.NewsRepository
import dagger.Component

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
@NewsScope
@Component(
    dependencies = [NetworkComponent::class],
    modules = [NewsModule::class]
)
interface NewsComponent {
    fun repo(): NewsRepository
}