package com.example.yuefenginterviewproject.ui.home.tabfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.ProductsEntity

class HomeLastAdapter (val frag: Fragment, var list: ArrayList<ProductsEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(frag.context).inflate(R.layout.item_home_last, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(newList: List<ProductsEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(list[position])
    }

    private inner class ViewHolder(pContentView: View) : RecyclerView.ViewHolder(pContentView) {
        private val ivProd: ImageView = pContentView.findViewById(R.id.qfItem01)
        private val tvTitle01: TextView = pContentView.findViewById(R.id.itemStr01)
        private val tvTitle02: TextView = pContentView.findViewById(R.id.itemStr02)

        fun bind(data: ProductsEntity) {
            tvTitle01.text = data.Title
            tvTitle02.text = data.Money

            if (frag.isAdded) {
                Glide.with(frag)
                    .load(data.ImgUrl)
                    .error(R.mipmap.ic_launcher)
                    .into(ivProd)

            }
        }
    }
}