package com.example.epam_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_project.data.Article
import com.example.epam_project.databinding.ItemFeaturedBinding
import com.example.epam_project.databinding.ItemStandardBinding

class ArticleAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Article>()
    private val TYPE_FEATURED = 0
    private val TYPE_STANDARD = 1

    fun submitList(list: List<Article>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(pos: Int) =
        if (items[pos].id % 5 == 0) TYPE_FEATURED else TYPE_STANDARD

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == TYPE_FEATURED) {
            val b = ItemFeaturedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            FeaturedVH(b)
        } else {
            val b = ItemStandardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            StandardVH(b)
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        val art = items[pos]
        (holder as? FeaturedVH)?.bind(art)
        (holder as? StandardVH)?.bind(art)
    }

    inner class FeaturedVH(val b: ItemFeaturedBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(a: Article) {
            b.textTitle.text = a.title
            b.root.setOnClickListener { onClick(a.id) }
        }
    }

    inner class StandardVH(val b: ItemStandardBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(a: Article) {
            b.textTitle.text = a.title
            b.root.setOnClickListener { onClick(a.id) }
        }
    }
}
