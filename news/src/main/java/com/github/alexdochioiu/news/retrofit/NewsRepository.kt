package com.github.alexdochioiu.news.retrofit

import com.github.alexdochioiu.news.NewsScope
import com.github.alexdochioiu.news.model.NewsModel
import kotlinx.coroutines.Deferred
import javax.inject.Inject

/**
 * Created by Alex Dochioiu on 22/03/2019
 */
@NewsScope
class NewsRepository @Inject internal constructor(private val newsService: NewsService) {

    fun fetchNewsAsync(
        query: String? = null
    ) : Deferred<NewsModel> = newsService.fetchArticlesAsync(query = query)
}