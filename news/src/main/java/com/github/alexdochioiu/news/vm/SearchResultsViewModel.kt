package com.github.alexdochioiu.news.vm

import androidx.lifecycle.ViewModel
import com.github.alexdochioiu.news.model.ArticleModel
import com.github.alexdochioiu.news.retrofit.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

/**
 * Created by Alexandru Iustin Dochioiu on 13-Apr-19
 *
 */
@ExperimentalCoroutinesApi
class SearchResultsViewModel internal constructor(private val newsRepository: NewsRepository) : ViewModel() {

    @ExperimentalCoroutinesApi
    val articlesChannel = BroadcastChannel<List<ArticleModel>>(1)

    init {
        // todo add Job for the coroutines to control their lifecycle
        GlobalScope.launch {
            newsRepository.fetchNewsAsync(query = "brexit").await().articles.let {
                articlesChannel.send(it)
            }
        }

    }

    fun getArticlesChannel() : ReceiveChannel<List<ArticleModel>> = articlesChannel.openSubscription()
}