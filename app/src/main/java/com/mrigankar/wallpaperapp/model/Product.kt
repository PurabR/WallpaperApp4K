package com.example.mvvm.repository.model

data class Product(
    val id: String,
    val link: String
)

data class Products(
    val products: List<Product>
)
