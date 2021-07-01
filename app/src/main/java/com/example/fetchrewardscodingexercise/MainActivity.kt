package com.example.fetchrewardscodingexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardscodingexercise.api.FetchRewardsAPI
import com.example.fetchrewardscodingexercise.model.Item
import com.example.fetchrewardscodingexercise.adapter.ItemAdapter
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData()
    }

    private fun fetchData() {
        val thread = Thread {
            val items = FetchRewardsAPI().getItems()
            updateUI(items)
        }
        thread.start()
    }

    private fun updateUI(items: List<Item>) {
        runOnUiThread {
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.adapter = ItemAdapter(items)
            recyclerView.setHasFixedSize(true)
        }
    }
}