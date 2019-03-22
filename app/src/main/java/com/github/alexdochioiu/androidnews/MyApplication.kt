package com.github.alexdochioiu.androidnews

import android.app.Application
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.network.DaggerNetworkComponent
import com.github.alexdochioiu.news.DaggerNewsComponent
import com.github.alexdochioiu.news.NewsComponent
import com.github.alexdochioiu.news.retrofit.NewsRepository
import dagger.Component
import javax.inject.Scope

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
class MyApplication : Application() {

    lateinit var component: AppComponent private set

    override fun onCreate() {
        super.onCreate()

        val networkComponent = DaggerNetworkComponent.builder()
            .appContext(this.applicationContext)
            .build()

        val newsComponent = DaggerNewsComponent.builder()
            .networkComponent(networkComponent)
            .build()

        component = DaggerMyApplication_AppComponent.builder()
            .newsComponent(newsComponent)
            .build().apply { inject(this@MyApplication) }
    }

    @Component(dependencies = [NewsComponent::class])
    @MyScope
    interface AppComponent : InjectableComponent<MyApplication> {
        fun newsRepo(): NewsRepository
    }

    @Retention(AnnotationRetention.SOURCE)
    @Scope
    annotation class MyScope
}