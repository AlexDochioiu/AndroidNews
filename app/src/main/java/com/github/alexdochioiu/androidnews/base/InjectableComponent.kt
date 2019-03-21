package com.github.alexdochioiu.androidnews.base

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
interface InjectableComponent<T : Any> {
    fun inject(parent: T) : T
}