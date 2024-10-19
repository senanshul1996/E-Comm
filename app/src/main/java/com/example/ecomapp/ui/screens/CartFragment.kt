package com.example.ecomapp.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomapp.adapter.CartAdapter
import com.example.ecomapp.databinding.FragmentCartBinding
import com.example.ecomapp.viewmodel.CartViewModel



class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                findNavController().popBackStack()
            }
        })


        cartAdapter = CartAdapter(emptyList())



        cartViewModel.totalCartValue.observe(viewLifecycleOwner) { total ->
            binding.textViewTotal.text = "Total: $$total"
        }

        binding.recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }


        cartViewModel.allCartItems.observe(viewLifecycleOwner){ cartList ->
            cartAdapter.updateProductList(cartList)
        }



      //  cartViewModel.getCartItemList()


        return binding.root
    }
}
