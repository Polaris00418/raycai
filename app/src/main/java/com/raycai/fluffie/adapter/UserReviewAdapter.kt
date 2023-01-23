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
import com.raycai.fluffie.data.model.UserReview

class UserReviewAdapter(private val reviews: List<UserReview>) :
    RecyclerView.Adapter<UserReviewAdapter.ViewHolder>() {

    open var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.li_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(reviews[position], listener)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        private val cvRoot: CardView = itemView.findViewById(R.id.cvRoot)

        fun setData(ur: UserReview, listener: Listener?) {
            cvRoot.setOnClickListener {
                listener?.onUserReviewClicked(ur)
            }
        }
    }

    interface Listener {
        fun onUserReviewClicked(ur: UserReview)
    }
}