package com.github.alexdochioiu.androidnews.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.alexdochioiu.news.retrofit.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Inject

/**
 * Created by Alexandru Iustin Dochioiu on 13-Apr-19
 *
 */
@UseExperimental(ObsoleteCoroutinesApi::class)
@ExperimentalCoroutinesApi
@SearchResultsFragment.MScope
class SearchResultsViewModelFactory @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SearchResultsViewModel(newsRepository) as T
    }
}