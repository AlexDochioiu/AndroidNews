package com.github.alexdochioiu.androidnews.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 */
abstract class BaseFragment<T : InjectableComponent<*>> : Fragment() {

    lateinit var component : T

    val fragmentActivity: FragmentActivity?
        get() = getActivity()

    open val activity: BaseActivity<*>?
        get() = getActivity()?.let { it as BaseActivity<*> }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buildDaggerComponentAndInject()
    }

    abstract fun buildDaggerComponentAndInject() : T
}