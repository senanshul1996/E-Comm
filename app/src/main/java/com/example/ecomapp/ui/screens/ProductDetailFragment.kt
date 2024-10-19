package com.example.ecomapp.ui.screens

import ProductViewModelFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ecomapp.data.remote.RetrofitInstance
import com.example.ecomapp.data.repository.ProductRepository
import com.example.ecomapp.databinding.FragmentProductDetailBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.ecomapp.data.local.CartDao
import com.example.ecomapp.data.local.CartDatabase
import com.example.ecomapp.data.local.CartItem
import com.example.ecomapp.viewmodel.CartViewModel
import com.example.ecomapp.viewmodel.ProductViewModel
import kotlinx.coroutines.launch
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback



class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var cartDao: CartDao
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root

        val db = Room.databaseBuilder(requireContext(), CartDatabase::class.java, "app-db").build()
        cartDao = db.cartDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })

        val repository = ProductRepository(RetrofitInstance.api)
        val factory = ProductViewModelFactory(repository)
        productViewModel = ViewModelProvider(this, factory).get(ProductViewModel::class.java)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        productViewModel.productDetails.observe(viewLifecycleOwner, Observer { product ->

            binding.productName.text = product.title
            binding.productDescription.text = product.description
            binding.productPrice.text = "$${product.price.toString()}"
             Glide.with(this).load(product.image).into(binding.productImage)

            binding.addToCartButton.setOnClickListener {

                val productId = product.id
                val productName = product.title
                val price = product.price
                val quantity = 1
                val image = product.image


                val cartItem = CartItem(productId, productName,image, price, quantity)

                cartViewModel.addToCart(cartItem)
            }

        })

        val productId = arguments?.getString("productId") ?: return
        productViewModel.getProductDetails(productId)

        binding.goToCartButton.setOnClickListener {
            navigateToCart()
        }

        cartViewModel.addCartResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                Log.d("Cart","Item added to cart successfully")
             //   Toast.makeText(requireContext(), "Item added to cart successfully", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("Cart","Failed to add item to cart")
             //   Toast.makeText(requireContext(), "Failed to add item to cart", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun navigateToCart() {
        val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
        findNavController().navigate(action)
    }

}
