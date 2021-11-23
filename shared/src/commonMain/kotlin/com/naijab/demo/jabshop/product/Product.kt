package com.naijab.demo.jabshop.product

import kotlinx.serialization.*

@Serializable
data class Rating(
    val rate : Double,
    val count : Int
)

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val image: String,
    val rating : Rating
)

@Serializable
data class ProductList(
    val products: List<Product>
)
