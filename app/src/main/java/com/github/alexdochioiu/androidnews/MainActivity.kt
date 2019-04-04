package com.github.alexdochioiu.androidnews

import android.os.Bundle
import com.github.alexdochioiu.androidnews.base.BaseActivity
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.androidnews.di.ActivityScope
import com.github.alexdochioiu.news.retrofit.NewsRepository
import dagger.Component

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
    @ActivityScope
    interface MComponent : InjectableComponent<MainActivity> {
        fun newsRepository(): NewsRepository
    }
}
