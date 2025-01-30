package com.example.yuefenginterviewproject.ui.searchview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.SearchProduct

class SearchRecommendListAdapter(val frag: Fragment, var list: ArrayList<SearchProduct>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(frag.context).inflate(R.layout.item_search_recommend, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(list[position])
    }

    private inner class ViewHolder(pContentView: View) : RecyclerView.ViewHolder(pContentView) {
        private val ivProd: ImageView = pContentView.findViewById(R.id.iv_prod)
        private val tvProdTitle: TextView = pContentView.findViewById(R.id.tv_prod_title)
        private val tvProdPrice: TextView = pContentView.findViewById(R.id.tv_prod_price)

        fun bind(data: SearchProduct) {
            tvProdTitle.text = data.Title
            tvProdPrice.text = data.Money
            if (frag.isAdded) {
                Glide.with(frag)
                    .load(data.ImgUrl)
                    .error(R.mipmap.ic_launcher)
                    .into(ivProd)

            }
            itemView.setOnClickListener {
                frag.context?.let {
                }
            }
        }
    }
}