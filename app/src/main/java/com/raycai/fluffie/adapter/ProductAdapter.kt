package com.raycai.fluffie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.raycai.fluffie.R
import com.raycai.fluffie.data.model.Product

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    open var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.li_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(products[position], listener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val cvRoot: CardView = itemView.findViewById(R.id.cvRoot)
        private val ivProductImg: ImageView = itemView.findViewById(R.id.ivProductImg)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvBrand: TextView = itemView.findViewById(R.id.tvBrand)
        private val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        private val tvReview: TextView = itemView.findViewById(R.id.tvReview)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

        fun setData(p: Product, listener: Listener?) {
            ivProductImg.setImageResource(p.imgRes)
            tvName.text = p.name
            tvBrand.text = p.brand
            tvRating.text = "${p.rating}"
            tvReview.text = "${p.reviews} reviews"
            tvPrice.text = p.price

            cvRoot.setOnClickListener {
                listener?.onProductClicked(p)
            }
        }
    }

    interface Listener {
        fun onProductClicked(p: Product)
    }
}