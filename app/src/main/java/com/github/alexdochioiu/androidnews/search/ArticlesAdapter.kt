package com.github.alexdochioiu.androidnews.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.alexdochioiu.androidnews.R
import com.github.alexdochioiu.androidnews.databinding.ItemArticleBinding
import com.github.alexdochioiu.news.model.ArticleModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Inject

/**
 * Created by Alexandru Iustin Dochioiu on 23-Mar-19
 *
 */
@ExperimentalCoroutinesApi
@UseExperimental(ObsoleteCoroutinesApi::class)
@SearchResultsFragment.MScope
class ArticlesAdapter @Inject constructor(val listener: Listener) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var articles: List<ArticleModel> = ArrayList()

    @MainThread
    fun replaceArticles(articles: List<ArticleModel>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_article, parent, false))

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles.get(position))
    }

    inner class ViewHolder(private val itemBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(articleModel: ArticleModel) {
            itemBinding.article = articleModel
            itemBinding.root.setOnClickListener { listener.onArticleSelected(articleModel) }

            Glide.with(itemView.context)
                .load(articleModel.urlToImage)
                .centerCrop()
                .into(itemBinding.itemArticleIvArticle)
        }
    }

    interface Listener {
        fun onArticleSelected(articleModel: ArticleModel)
    }
}