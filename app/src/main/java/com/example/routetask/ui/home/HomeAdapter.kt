package com.example.routetask.ui.home

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.routetask.R
import com.example.routetask.model.entities.Product
import java.util.Locale

class HomeAdapter(private val context: Context) :
    ListAdapter<Product, ProductViewHolder>(ProductDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = getItem(position)


        val thumbnail = product.thumbnail
        holder.tvProductName.text = product.title
        holder.tvProductDesc.text = product.description
        holder.tvFinalPrice.text = context.getString(R.string.egp, product.price.toString())
        holder.tvRealPrice.text = calculateRealPrice(product.price, product.discountPercentage)
        holder.tvRealPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.tvProductReview.text = context.getString(R.string.review, product.rating.toString())





        Glide.with(context)
            .load(thumbnail)
            .into(holder.ivProduct)
    }

}


class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
    val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
    val tvProductDesc: TextView = itemView.findViewById(R.id.tv_product_description)
    val tvFinalPrice: TextView = itemView.findViewById(R.id.tv_final_price)
    val tvRealPrice: TextView = itemView.findViewById(R.id.tv_real_price)
    val tvProductReview: TextView = itemView.findViewById(R.id.tv_product_review)


}

class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

fun calculateRealPrice(finalPrice: Double, discount: Double): String {

    val realPrice = discount / 100 * finalPrice + finalPrice

    return String.format(Locale.US, "%.2f", realPrice)

}
