package com.example.ecomapp.data.repository

import com.example.ecomapp.data.model.ProductModel
import com.example.ecomapp.data.model.ProductModelItem
import com.example.ecomapp.data.remote.ProductApi
import retrofit2.Response

class ProductRepository(private val api: ProductApi) {

    suspend fun getProduct( ): Response<ProductModel> {
        return api.getProducts()
    }

    suspend fun getProductById(productId: String): Response<ProductModelItem> {
        return api.getProductById(productId)
    }
}