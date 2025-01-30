package com.example.yuefenginterviewproject.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.CartItemsEntity

class CartItemListAdapter (val context: Context, var list: ArrayList<CartItemsEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_cart_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(list[position])
    }

    fun updateList(newList: List<CartItemsEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private inner class ViewHolder(pContentView: View) : RecyclerView.ViewHolder(pContentView) {
        private val eventTitle: TextView = pContentView.findViewById(R.id.cartTitle)
        private val cartItemImg: ImageView = pContentView.findViewById(R.id.cartItemImg)

        fun bind(data: CartItemsEntity) {

            eventTitle.text = data.Title

            Glide.with(context)
                .load(data.mPic)
                .error(R.mipmap.ic_launcher)
                .into(cartItemImg)
        }
    }
}