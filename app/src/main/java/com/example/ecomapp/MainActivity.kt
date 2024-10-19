package com.example.ecomapp


import ProductViewModelFactory
import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomapp.adapter.ProductAdapter
import com.example.ecomapp.data.model.ProductModel
import com.example.ecomapp.data.model.ProductModelItem
import com.example.ecomapp.data.remote.RetrofitInstance
import com.example.ecomapp.data.repository.ProductRepository
import com.example.ecomapp.databinding.ActivityMainBinding
import com.example.ecomapp.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var productViewModel: ProductViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val recyclerView = binding.recyclerView
//        val repository = ProductRepository(RetrofitInstance.api)
//        val factory = ProductViewModelFactory(repository)
//        productViewModel = ViewModelProvider(this,factory).get(ProductViewModel::class.java)
//
//
//        // Initialize adapter with an empty list
//        productAdapter = ProductAdapter(ProductModel())
//
//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = productAdapter
//        }
//
//
//        // Observe product list from ViewModel
//        productViewModel.products.observe(this, Observer { productList ->
//
//            productAdapter.updateProductList(productList)
//
//        })



        // Fetch product list
    //    productViewModel.fetchProducts()
    }
}