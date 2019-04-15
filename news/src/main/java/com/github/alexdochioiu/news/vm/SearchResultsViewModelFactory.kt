package com.github.alexdochioiu.news.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.alexdochioiu.news.retrofit.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Alexandru Iustin Dochioiu on 13-Apr-19
 *
 */
@ExperimentalCoroutinesApi
class SearchResultsViewModelFactory @Inject internal constructor(private val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SearchResultsViewModel(newsRepository) as T
    }
}