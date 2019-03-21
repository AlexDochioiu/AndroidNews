package com.github.alexdochioiu.androidnews

import android.app.Application
import com.github.alexdochioiu.network.DaggerNetworkComponent
import com.github.alexdochioiu.network.NetworkComponent
import com.github.alexdochioiu.news.DaggerNewsComponent
import com.github.alexdochioiu.news.NewsComponent
import dagger.Component
import javax.inject.Scope

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
class MyApplication : Application() {

    lateinit var component: MyComponent private set

    override fun onCreate() {
        super.onCreate()

        val networkComponent = DaggerNetworkComponent.builder()
            .build()

        val newsComponent = DaggerNewsComponent.builder()
            .networkComponent(networkComponent)
            .build()

        component = DaggerMyApplication_MyComponent.builder()
            .newsComponent(newsComponent)
            .build().apply { inject(this@MyApplication) }
    }

    @Component(dependencies = [NewsComponent::class])
    @MyScope
    interface MyComponent {
        fun inject(myApplication: MyApplication)
    }

    @Retention(AnnotationRetention.SOURCE)
    @Scope
    annotation class MyScope
}