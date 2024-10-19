package com.example.ecomapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecomapp.data.local.CartDao
import com.example.ecomapp.data.local.CartItem

class CartRepository(private val cartDao: CartDao) {
    val allCartItems: LiveData<List<CartItem>> = cartDao.getAllCartItems()
    val totalCartValue: LiveData<Double> = cartDao.getTotalCartValue()

    suspend fun addToCart(cartItem: CartItem) {
        cartDao.addToCart(cartItem)
    }

    suspend fun removeFromCart(cartItem: CartItem) {
        cartDao.removeFromCart(cartItem)
    }

    suspend fun updateCartItem(cartItem: CartItem) {
        cartDao.updateCartItem(cartItem)
    }

}
