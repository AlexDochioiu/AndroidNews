package com.github.alexdochioiu.androidnews.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.alexdochioiu.androidnews.MainActivity
import com.github.alexdochioiu.androidnews.R
import com.github.alexdochioiu.androidnews.base.BaseFragment
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.news.retrofit.NewsRepository
import dagger.Component
import kotlinx.android.synthetic.main.fragment_search_results.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Scope

class SearchResultsFragment : BaseFragment<SearchResultsFragment.MComponent>() {

    override val activity get() = super.activity.let { it as MainActivity }

    @Inject
    lateinit var newsRepository: NewsRepository

    override fun buildDaggerComponentAndInject(): MComponent = DaggerSearchResultsFragment_MComponent.builder()
        .mComponent(activity.component)
        .build().apply { inject(this@SearchResultsFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search_results, container, false)

        val myAdapter = ArticlesAdapter()

        rootView.rvSearchResults.layoutManager = LinearLayoutManager(inflater.context)
        rootView.rvSearchResults.adapter = myAdapter

        GlobalScope.launch {
            val result = newsRepository.fetchNewsAsync(query = "brexit").await().also {
                GlobalScope.launch(Dispatchers.Main) {
                    myAdapter.replaceArticles(it.articles)
                }


            }

            Timber.i(result.toString().substring(0, 1000))
        }

        return rootView
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchResultsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    @Component(dependencies = [MainActivity.MComponent::class])
    @MScope
    interface MComponent : InjectableComponent<SearchResultsFragment>

    @Retention(AnnotationRetention.SOURCE)
    @Scope
    annotation class MScope
}
