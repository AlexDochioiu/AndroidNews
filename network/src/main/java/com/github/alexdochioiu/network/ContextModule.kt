package com.github.alexdochioiu.network

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Alex Dochioiu on 22/03/2019
 */
@Module
internal class ContextModule(context: Context) {
    internal val appContext = context.applicationContext
        @Provides
        @NetworkScope
        get
}