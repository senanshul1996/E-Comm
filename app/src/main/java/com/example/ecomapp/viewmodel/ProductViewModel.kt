package com.example.ecomapp.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomapp.data.model.ProductModel
import com.example.ecomapp.data.model.ProductModelItem
import com.example.ecomapp.data.remote.RetrofitInstance
import com.example.ecomapp.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<ProductModel>()
    val products: LiveData<ProductModel> get() = _products

    private val _productDetails = MutableLiveData<ProductModelItem>()
    val productDetails: LiveData<ProductModelItem> get() = _productDetails


     fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = productRepository.getProduct()
                if (response.isSuccessful) {

                    _products.postValue(response.body())
                    Log.e("ViewModel", " Data Updated: ${_products.value}")
                } else {
                    Log.e("ViewModel", "Failed to fetch  data: ${response.errorBody()?.string()}")
                }
            }catch (e : Exception){
                Log.e("ViewModel", " Error: ${e}")
            }
        }
    }




    fun getProductDetails(productId: String) {

        viewModelScope.launch {


            try {
                val response = productRepository.getProductById(productId)
                if (response.isSuccessful) {

                    _productDetails.value = response.body()

                    Log.e("ViewModel", " Data Updated: ${_productDetails.value}")

                } else {
                    Log.e("ViewModel", "Failed to fetch  data: ${response.errorBody()?.string()}")
                }
            }catch (e : Exception){
                Log.e("ViewModel", " Error: ${e}")
            }
        }

    }
}
