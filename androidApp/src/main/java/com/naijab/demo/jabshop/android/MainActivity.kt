package com.naijab.demo.jabshop.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.naijab.demo.jabshop.product.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        ProductAPI().getProducts(
            success = {
                println("Product API -> $it")
            },
            failure = {
                println("Product API Error -> $it")
            }
        )
    }
}
