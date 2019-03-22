package com.github.alexdochioiu.androidnews.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.alexdochioiu.androidnews.MyApplication

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity<T : InjectableComponent<*>> : AppCompatActivity() {

    val application get() = super.getApplication() as MyApplication

    lateinit var component: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = buildDaggerComponentAndInject()
    }

    abstract fun buildDaggerComponentAndInject(): T
}