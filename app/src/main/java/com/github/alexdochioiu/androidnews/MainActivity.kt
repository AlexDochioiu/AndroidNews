package com.github.alexdochioiu.androidnews

import android.os.Bundle
import com.github.alexdochioiu.androidnews.base.BaseActivity
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.news.retrofit.NewsRepository
import dagger.Component
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

        GlobalScope.launch {
            val result = newsRepository.fetchNewsAsync(query = "brexit").await()
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
