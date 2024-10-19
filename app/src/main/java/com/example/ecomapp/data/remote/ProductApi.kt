package com.example.ecomapp.data.remote

import com.example.ecomapp.data.model.ProductModel
import com.example.ecomapp.data.model.ProductModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): Response<ProductModel>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<ProductModelItem>
}