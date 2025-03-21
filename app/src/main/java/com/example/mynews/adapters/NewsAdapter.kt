package com.example.mynews.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews.DetailActivity
import com.example.mynews.R
import com.example.mynews.models.Article

class NewsAdapter(private val articles: List<Article>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsTitle: TextView = itemView.findViewById(R.id.newsTitle)
        val newsImage: ImageView = itemView.findViewById(R.id.newsImage)

        fun bind(article: Article) {
            newsTitle.text = article.title
            Glide.with(itemView.context)
                .load(article.urlToImage ?: R.drawable.placeholder)
                .into(newsImage)

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("title", article.title)
                    putExtra("imageUrl", article.urlToImage)
                    putExtra("content", article.content)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}


