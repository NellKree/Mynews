package com.example.mynews

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title")
        val imageUrl = intent.getStringExtra("imageUrl")
        val content = intent.getStringExtra("content")

        findViewById<TextView>(R.id.detailTitle).text = title
        findViewById<TextView>(R.id.detailContent).text = content
        Glide.with(this).load(imageUrl).into(findViewById(R.id.detailImage))
    }

}
