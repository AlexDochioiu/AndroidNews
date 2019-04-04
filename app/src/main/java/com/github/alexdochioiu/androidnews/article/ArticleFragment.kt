package com.github.alexdochioiu.androidnews.article


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.github.alexdochioiu.androidnews.MainActivity
import com.github.alexdochioiu.androidnews.R
import com.github.alexdochioiu.androidnews.base.BaseFragment
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import com.github.alexdochioiu.androidnews.di.FragmentScope
import dagger.Component
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 *
 */
class ArticleFragment : BaseFragment<ArticleFragment.ArticleFragmentComponent>() {

    override val activity get() = super.activity.let { it as MainActivity }

    private val args: ArticleFragmentArgs by navArgs()

    override fun buildDaggerComponentAndInject(): ArticleFragmentComponent = DaggerArticleFragment_ArticleFragmentComponent.builder()
        .mComponent(activity.component)
        .build().apply { inject(this@ArticleFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false).also {
            Timber.i(args.ARGARTICLE.toString())
        }
    }

    @Component(dependencies = [MainActivity.MComponent::class])
    @FragmentScope
    interface ArticleFragmentComponent : InjectableComponent<ArticleFragment>
}
