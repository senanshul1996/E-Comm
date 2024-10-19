package com.example.ecomapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart_items")
data class CartItem(//@PrimaryKey(autoGenerate = true) val id: Int = 0,
                    @PrimaryKey val productId: Int,
                    val productName: String,
                    val image : String,
                    val price: Double,
                    var quantity: Int)
