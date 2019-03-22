package com.github.alexdochioiu.androidnews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.alexdochioiu.androidnews.base.BaseActivity
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.news.retrofit.NewsRepository
import dagger.Component
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Scope

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildDaggerComponentAndInject()
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val result = newsRepository.fetchNewsAsync(query = "brexit").await()
            Log.i("data", result.toString())
        }
    }

    fun buildDaggerComponentAndInject() : MComponent =
        DaggerMainActivity_MComponent.builder()
            .appComponent(application.let { it as MyApplication }.component)
            .build().apply { inject(this@MainActivity) }

    @Component(dependencies = [MyApplication.AppComponent::class])
    @MScope
    interface MComponent {
        fun inject(mainActivity: MainActivity) : MainActivity
    }
    //interface MComponent : InjectableComponent<MainActivity>

    @Retention(AnnotationRetention.SOURCE)
    @Scope
    annotation class MScope
}
