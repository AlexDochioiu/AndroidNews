package com.github.alexdochioiu.androidnews.core

/**
 * Created by Alex Dochioiu on 15/04/2019
 */
interface SingleListener<A> {
    fun run(first: A)
}

interface DualListener<A, B> {
    fun run(first: A, second: B)
}

interface TripleListener<A, B, C> {
    fun run(first: A, second: B, third: C)
}