package com.github.alexdochioiu.network

import android.content.Context
import com.github.alexdochioiu.network.retrofit.RetrofitFactory
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 */
@NetworkScope
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun retrofitFactory(): RetrofitFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): NetworkComponent
    }
}