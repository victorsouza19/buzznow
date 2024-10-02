package com.victorsouza19.buzznow.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.victorsouza19.buzznow.adapters.ArticleListAdapter
import com.victorsouza19.buzznow.api.RetrofitService
import com.victorsouza19.buzznow.databinding.ActivityMainBinding
import com.victorsouza19.buzznow.repositories.MainRepository
import com.victorsouza19.buzznow.viewmodel.main.MainViewModel
import com.victorsouza19.buzznow.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = ArticleListAdapter {
        goToUrl(it.url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )

        binding.articleList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer { articles ->
            adapter.setItems(articles)
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEverything()
    }

    fun goToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }
}