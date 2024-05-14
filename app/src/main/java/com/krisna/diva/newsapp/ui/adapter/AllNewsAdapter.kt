package com.krisna.diva.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krisna.diva.newsapp.R
import com.krisna.diva.newsapp.data.remote.response.ArticlesItem
import com.krisna.diva.newsapp.databinding.ItemAllNewsBinding
import com.krisna.diva.newsapp.utils.DateFormatter

class AllNewsAdapter : PagingDataAdapter<ArticlesItem, AllNewsAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAllNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = getItem(position)
        if (news != null) {
            holder.bind(news)
        }
    }

    class MyViewHolder(private val binding: ItemAllNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticlesItem) {
            with(binding) {
                tvAllTitle.text = article.title
                tvAllSource.text = article.source?.name
                tvAllDate.text = article.publishedAt?.let { DateFormatter.convertDate(it) }
                if (article.urlToImage != null) {
                    Glide.with(root)
                        .load(article.urlToImage)
                        .into(ivAllPhoto)
                } else {
                    ivAllPhoto.setImageResource(R.drawable.no_image)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}