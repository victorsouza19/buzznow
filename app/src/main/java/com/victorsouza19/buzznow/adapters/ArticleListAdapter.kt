package com.victorsouza19.buzznow.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.victorsouza19.buzznow.R
import com.victorsouza19.buzznow.api.models.Article
import com.victorsouza19.buzznow.databinding.ResItemArticleBinding

class ArticleListAdapter(private val onItemClicked: (Article) -> Unit) : RecyclerView.Adapter<ArticleViewHolder>() {

    private var articles = mutableListOf<Article>()

    fun setItems(lives: List<Article>) {
        this.articles = lives.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val live = articles[position]
        holder.bind(live, onItemClicked)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

class ArticleViewHolder(val binding: ResItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onItemClicked: (Article) -> Unit) {

        binding.title.text = article.title
        binding.author.text = article.author

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(article.urlToImage)
            .into(binding.thumbnail)

        itemView.setOnClickListener {
            onItemClicked(article)
        }

    }

}