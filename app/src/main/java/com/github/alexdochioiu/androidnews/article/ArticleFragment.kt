package com.github.alexdochioiu.androidnews.article


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.alexdochioiu.androidnews.MainActivity
import com.github.alexdochioiu.androidnews.R
import com.github.alexdochioiu.androidnews.base.BaseFragment
import com.github.alexdochioiu.androidnews.base.InjectableComponent
import dagger.Component
import javax.inject.Scope

/**
 * A simple [Fragment] subclass.
 *
 */
class ArticleFragment : BaseFragment<ArticleFragment.MComponent>() {

    override val activity get() = super.activity.let { it as MainActivity }

    //val args: ArticleFragmentArgs by navArgs()

    override fun buildDaggerComponentAndInject(): MComponent = DaggerArticleFragment_MComponent.builder()
        .mComponent(activity.component)
        .build().apply { inject(this@ArticleFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    @Component(dependencies = [MainActivity.MComponent::class])
    @MScope
    interface MComponent : InjectableComponent<ArticleFragment>

    @Retention(AnnotationRetention.SOURCE)
    @Scope
    annotation class MScope
}
