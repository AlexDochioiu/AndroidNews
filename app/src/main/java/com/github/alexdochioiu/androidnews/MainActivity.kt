package com.github.alexdochioiu.androidnews

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.alexdochioiu.androidnews.base.BaseActivity
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.news.retrofit.NewsRepository
import dagger.Component
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Scope

class MainActivity : BaseActivity<MainActivity.MComponent>() {

    @Inject
    lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myAdapter = ArticlesAdapter()

        activity_main_rv.layoutManager = LinearLayoutManager(this)
        activity_main_rv.adapter = myAdapter

        GlobalScope.launch {
            val result = newsRepository.fetchNewsAsync(query = "brexit").await().also {
                runOnUiThread {
                    myAdapter.replaceArticles(it.articles)
                }
            }

            Timber.i(result.toString().substring(0, 1000))
        }
    }

    override fun buildDaggerComponentAndInject() : MComponent =
        DaggerMainActivity_MComponent.builder()
            .appComponent(application.component)
            .build().apply { inject(this@MainActivity) }

    @Component(dependencies = [MyApplication.AppComponent::class])
    @MScope
    interface MComponent : InjectableComponent<MainActivity>

    @Retention(AnnotationRetention.SOURCE)
    @Scope
    annotation class MScope
}
