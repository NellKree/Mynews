package com.example.mynews

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.mynews.adapters.NewsAdapter
import com.example.mynews.network.RetrofitInstance
import com.example.mynews.network.isNetworkAvailable
import com.example.mynews.repository.NewsRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var repository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val themeButton: Button = findViewById(R.id.switchThemeButton)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        repository = NewsRepository(RetrofitInstance.api, this)

        if (!isNetworkAvailable(this)) {

            Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show()
        } else {
            loadNews()
        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            themeButton.text = "Светлая тема"
        } else {
            themeButton.text = "Тёмная тема"
        }

        themeButton.setOnClickListener {
            toggleTheme()
        }


    }
    private fun toggleTheme() {
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            recreate()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            recreate()
        }
    }
    private fun loadNews() {
        lifecycleScope.launch {
            try {
                val newsResponse = repository.getNews()
                recyclerView.adapter = NewsAdapter(newsResponse.articles, this@MainActivity)
            } catch (e: Exception) {
                Log.e("MainActivity", "Ошибка загрузки новостей", e)
            }
        }
    }
}
