package com.github.alexdochioiu.androidnews.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.alexdochioiu.androidnews.MainActivity
import com.github.alexdochioiu.androidnews.R
import com.github.alexdochioiu.androidnews.base.BaseFragment
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.news.model.ArticleModel
import com.github.alexdochioiu.news.vm.SearchResultsViewModel
import com.github.alexdochioiu.news.vm.SearchResultsViewModelFactory
import dagger.BindsInstance
import dagger.Component
import kotlinx.android.synthetic.main.fragment_search_results.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import javax.inject.Inject
import javax.inject.Scope

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class SearchResultsFragment : BaseFragment<SearchResultsFragment.MComponent>(), ArticlesAdapter.Listener {

    override val activity get() = super.activity.let { it as MainActivity }

    @Inject
    lateinit var viewModelFactory: SearchResultsViewModelFactory

    @Inject
    lateinit var articlesAdapter: ArticlesAdapter

    lateinit var viewModel: SearchResultsViewModel

    override fun buildDaggerComponentAndInject(): MComponent = DaggerSearchResultsFragment_MComponent.factory()
        .create(activity.component, this)
        .apply { inject(this@SearchResultsFragment) }

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

        rootView.rvSearchResults.layoutManager = LinearLayoutManager(inflater.context)
        rootView.rvSearchResults.adapter = articlesAdapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchResultsViewModel::class.java)

        //todo create Job for coroutine
        GlobalScope.launch {
            viewModel.getArticlesChannel().consumeEach {
                GlobalScope.launch(Dispatchers.Main) { articlesAdapter.replaceArticles(it)  }
            }
        }

        return rootView
    }

    override fun onArticleSelected(articleModel: ArticleModel) {
        SearchResultsFragmentDirections.actionSearchResultsFragmentToArticleFragment(articleModel).let {
            NavHostFragment.findNavController(this).navigate(it)
        }
    }

    @Component(dependencies = [MainActivity.MComponent::class])
    @MScope
    interface MComponent : InjectableComponent<SearchResultsFragment> {

        @Component.Factory
        interface Factory {
            fun create(
                component: MainActivity.MComponent,
                @BindsInstance listener: ArticlesAdapter.Listener
            ): MComponent
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    @Scope
    annotation class MScope
}
