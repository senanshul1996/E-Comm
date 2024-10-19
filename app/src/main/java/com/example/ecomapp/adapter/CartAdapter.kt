package com.example.ecomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ecomapp.data.local.CartItem
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomapp.adapter.ProductAdapter.ProductViewHolder
import com.example.ecomapp.data.model.ProductModel
import com.example.ecomapp.data.model.ProductModelItem
import com.example.ecomapp.databinding.ItemCartBinding




class CartAdapter(private var cartList: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return CartViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val product = cartList[position]
        holder.binding.apply {
            productName.text = product.productName
            priceTV.text = "$${product.price}"
            quantityTV.text = "Qty: ${product.quantity.toString()}"
            Glide.with(productImage.context).load(product.image).into(productImage)
        }

    }

    override fun getItemCount(): Int = cartList.size

    fun updateProductList(cartList: List<CartItem>) {
        this.cartList = cartList
        notifyDataSetChanged()
    }

}
