package com.example.ecomapp.ui.screens

import ProductViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomapp.data.model.ProductModel
import com.example.ecomapp.data.remote.RetrofitInstance
import com.example.ecomapp.data.repository.ProductRepository
import com.example.ecomapp.databinding.FragmentProductListBinding
import androidx.navigation.fragment.findNavController
import com.example.ecomapp.adapter.ProductAdapter
import com.example.ecomapp.viewmodel.ProductViewModel


class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ProductRepository(RetrofitInstance.api)
        val factory = ProductViewModelFactory(repository)
        productViewModel = ViewModelProvider(this, factory).get(ProductViewModel::class.java)

        productAdapter = ProductAdapter(ProductModel()) { product ->
            navigateToDetail(product.id.toString())
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }

        productViewModel.products.observe(viewLifecycleOwner) { productList ->
            productAdapter.updateProductList(productList)
        }

        productViewModel.fetchProducts()

    }

    private fun navigateToDetail(productId: String) {
        val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(productId)
        findNavController().navigate(action)
    }
}