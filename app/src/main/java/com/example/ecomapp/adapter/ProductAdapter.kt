package com.example.ecomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomapp.data.model.ProductModel
import com.example.ecomapp.data.model.ProductModelItem
import com.example.ecomapp.databinding.ItemProductBinding

class ProductAdapter(private var productList: ProductModel, private val onClick: (ProductModelItem) -> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            productName.text = product.title
            productPrice.text = "$${product.price}"
            Glide.with(productImage.context).load(product.image).into(productImage)
        }

        // Handle click events
        holder.itemView.setOnClickListener {
            onClick(product)
        }
    }

    // Method to update product list dynamically
    fun updateProductList(newProductList: ProductModel) {
        productList = newProductList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = productList.size
}
