package com.krisna.diva.newsapp.ui

import org.koin.androidx.viewmodel.ext.android.viewModel
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.krisna.diva.newsapp.R
import com.krisna.diva.newsapp.databinding.ActivityMainBinding
import com.krisna.diva.newsapp.ui.adapter.AllNewsAdapter
import com.krisna.diva.newsapp.ui.adapter.HeadlineNewsAdapter
import com.krisna.diva.newsapp.ui.adapter.LoadingStateAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val allNewsAdapter = AllNewsAdapter()
    private val headlineNewsAdapter = HeadlineNewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.show()
        setupRecyclerView()
        observeNewsData()
    }

    private fun setupRecyclerView() {
        binding.rvAllNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvHeadlineNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvAllNews.adapter = allNewsAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter { allNewsAdapter.retry() }
        )
        binding.rvHeadlineNews.adapter = headlineNewsAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter { headlineNewsAdapter.retry() }
        )
    }

    private fun observeNewsData() {

        viewModel.listAllNews.observe(this) {
            allNewsAdapter.submitData(lifecycle, it)
        }

        viewModel.listHeadlineNews.observe(this) {
            headlineNewsAdapter.submitData(lifecycle, it)
        }
    }
}