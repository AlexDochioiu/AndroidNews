package com.github.alexdochioiu.androidnews

import android.app.Application
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.network.DaggerNetworkComponent
import com.github.alexdochioiu.news.DaggerNewsComponent
import com.github.alexdochioiu.news.NewsComponent
import com.github.alexdochioiu.news.retrofit.NewsRepository
import dagger.Component
import timber.log.Timber
import javax.inject.Scope

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
class MyApplication : Application() {

    lateinit var component: AppComponent private set

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val networkComponent = DaggerNetworkComponent.factory()
            .create(this.applicationContext)

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