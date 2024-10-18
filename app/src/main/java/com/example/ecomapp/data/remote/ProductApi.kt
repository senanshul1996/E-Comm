package com.example.ecomapp.data.remote

import com.example.ecomapp.data.model.ProductModelItem
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): List<ProductModelItem>
}