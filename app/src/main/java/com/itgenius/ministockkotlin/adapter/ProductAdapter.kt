package com.itgenius.ministockkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itgenius.ministockkotlin.databinding.AdapterProductBinding
import com.itgenius.ministockkotlin.model.ProductModel

class ProductAdapter : RecyclerView.Adapter<MainViewHolder>() {

    // สร้างตัวแปรไว้เก็บรายชื่อสินค้า
    private var products = mutableListOf<ProductModel>()

    fun setProductList(products: List<ProductModel>){
        this.products = products.toMutableList()
//        notifyDataSetChanged()
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterProductBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val product = products[position]
        holder.binding.productName.text = product.productName  // product.ProductName
        holder.binding.categoryName.text = product.categoryName  // product.category[0].CategoryName
        holder.binding.unitPrice.text = product.unitPrice.toString() // product.UnitPrice.toString()
        Glide.with(holder.itemView.context).load(product.productPicture).into(holder.binding.imageProduct) // product.ProductPicture

        // Event Click on Item
        holder.itemView.setOnClickListener { view ->
            Toast.makeText(
                view.context,
                "Item $position Product ID ${product.productID}",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }

}

class MainViewHolder(val binding: AdapterProductBinding): RecyclerView.ViewHolder(binding.root)