package com.github.alexdochioiu.androidnews.base

import androidx.appcompat.app.AppCompatActivity
import com.github.alexdochioiu.androidnews.MyApplication

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 *
 */
@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity: AppCompatActivity() {

    val application = super.getApplication() as MyApplication

}