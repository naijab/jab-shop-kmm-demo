package com.naijab.demo.jabshop

import io.ktor.client.*
import io.ktor.http.*
import io.ktor.client.request.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class ProductAPI {
    private val client = HttpClient()
    private val productsPath = Url("https://fakestoreapi.com/products")

    fun getProducts(success: (List<Product>) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val json: String = client.get {
                    url(productsPath.toString())
                }
                Json.decodeFromString<List<Product>>(json).also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }
}
