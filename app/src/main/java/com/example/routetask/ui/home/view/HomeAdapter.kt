package com.example.routetask.ui.home.view

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
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
    ListAdapter<Product, ProductViewHolder>(ProductDiffUtil()), Filterable {

    private var productListFull: List<Product> = emptyList()

    override fun submitList(list: List<Product>?) {
        super.submitList(list)
        if (list != null) {
            productListFull = ArrayList(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = getItem(position)
        holder.bindData(product, context)

    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<Product> = ArrayList()

                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(productListFull)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()

                    for (item in productListFull) {
                        if (item.title.toLowerCase(Locale.ROOT).contains(filterPattern) ||
                            item.description.toLowerCase(Locale.ROOT).contains(filterPattern)
                        ) {
                            filteredList.add(item)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                submitList(results?.values as List<Product>)
            }
        }
    }

}


class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
    private val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
    private val tvProductDesc: TextView = itemView.findViewById(R.id.tv_product_description)
    private val tvDiscountedPrice: TextView = itemView.findViewById(R.id.tv_discounted_price)
    private val tvOriginalPrice: TextView = itemView.findViewById(R.id.tv_original_price)
    private val tvProductReview: TextView = itemView.findViewById(R.id.tv_product_review)


    fun bindData(product: Product, context: Context) {
        val thumbnail = product.thumbnail
        tvProductName.text = product.title
        tvProductDesc.text = product.description
        tvDiscountedPrice.text = context.getString(R.string.egp, product.price.toString())
        "${calculateOriginalPrice(product.price, product.discountPercentage)} EGP".also { tvOriginalPrice.text = it }
        tvOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        tvProductReview.text = context.getString(R.string.review, product.rating.toString())

        Glide.with(context)
            .load(thumbnail)
            .into(ivProduct)
    }


}

class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

fun calculateOriginalPrice(discountedPrice: Double, discountPercentage: Double): String {

    val realPrice = discountPercentage / 100 * discountedPrice + discountedPrice

    return String.format(Locale.US, "%.2f", realPrice)

}
