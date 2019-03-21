package com.github.alexdochioiu.androidnews.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Created by Alexandru Iustin Dochioiu on 21-Mar-19
 */
class BaseFragment : Fragment() {
    val fragmentActivity: FragmentActivity?
        get() = getActivity()

    val activity: BaseActivity?
        get() = getActivity()?.let { it as BaseActivity }
}