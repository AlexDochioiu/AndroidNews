package com.github.alexdochioiu.news

import com.github.alexdochioiu.network.NetworkComponent
import dagger.Component

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
@NewsScope
@Component(dependencies = [NetworkComponent::class])
interface NewsComponent