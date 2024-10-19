package com.example.ecomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecomapp.data.local.CartDatabase
import com.example.ecomapp.data.local.CartItem
import com.example.ecomapp.data.model.ProductModel
import com.example.ecomapp.data.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CartRepository
    var allCartItems: LiveData<List<CartItem>>
    val totalCartValue: LiveData<Double>

    private var _cartList = MutableLiveData<List<CartItem>>()
    val cartList: LiveData<List<CartItem>> get() = _cartList

    private val _addCartResult = MutableLiveData<Boolean>()
    val addCartResult: LiveData<Boolean> get() = _addCartResult

    init {
        val cartDao = CartDatabase.getDatabase(application).cartDao()
        repository = CartRepository(cartDao)
        allCartItems = repository.allCartItems
        totalCartValue = repository.totalCartValue
    }


    fun addToCart(cartItem: CartItem) = viewModelScope.launch {
        try {
            repository.addToCart(cartItem)
            _addCartResult.postValue(true)
        } catch (e: Exception) {
            _addCartResult.postValue(false)
        }
    }

    fun removeFromCart(cartItem: CartItem) = viewModelScope.launch {
        repository.removeFromCart(cartItem)
    }

    fun updateCartItem(cartItem: CartItem) = viewModelScope.launch {
        repository.updateCartItem(cartItem)
    }

}
