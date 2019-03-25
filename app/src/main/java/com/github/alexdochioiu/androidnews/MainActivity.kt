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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun buildDaggerComponentAndInject() : MComponent =
        DaggerMainActivity_MComponent.builder()
            .appComponent(application.component)
            .build().apply { inject(this@MainActivity) }

    @Component(dependencies = [MyApplication.AppComponent::class])
    @MScope
    interface MComponent : InjectableComponent<MainActivity> {
        fun newsRepository(): NewsRepository
    }

    @Retention(AnnotationRetention.SOURCE)
    @Scope
    annotation class MScope
}
