package com.github.alexdochioiu.androidnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.alexdochioiu.androidnews.databinding.ItemArticleBinding
import com.github.alexdochioiu.news.model.ArticleModel

/**
 * Created by Alexandru Iustin Dochioiu on 23-Mar-19
 *
 */
class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var articles: List<ArticleModel> = ArrayList()

    fun replaceArticles(articles: List<ArticleModel>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_article, parent, false))

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles.get(position))
    }

    inner class ViewHolder(private val itemBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(articleModel: ArticleModel) {
            itemBinding.article = articleModel
            Glide.with(itemView.context)
                .load(articleModel.urlToImage)
                .into(itemBinding.itemArticleIvArticle)
        }
    }
}