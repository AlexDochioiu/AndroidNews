package com.github.alexdochioiu.network

import com.github.alexdochioiu.network.retrofit.RetrofitFactory
import dagger.Component

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 */
@NetworkScope
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun retrofitFactory(): RetrofitFactory
}