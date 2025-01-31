package com.example.yuefenginterviewproject.ui.bestsellers.subview3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.BestsellersEntity

class SubBestsellers01Adapter (val frag: Fragment, var list: ArrayList<BestsellersEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(frag.context).inflate(R.layout.item_bestsellers, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(list[position])
    }

    private inner class ViewHolder(pContentView: View) : RecyclerView.ViewHolder(pContentView) {
        private val ivProd: ImageView = pContentView.findViewById(R.id.iv_prod_frame)
        private val tvTitle01: TextView = pContentView.findViewById(R.id.title01)
        private val tvTitle02: TextView = pContentView.findViewById(R.id.title02)
        private val tvmoney1: TextView = pContentView.findViewById(R.id.money1)

        fun bind(data: BestsellersEntity) {
            tvTitle01.text = data.Title
            tvTitle02.text = data.subTitle
            tvmoney1.text = data.Money

            if (frag.isAdded) {
                Glide.with(frag)
                    .load(data.ImgUrl)
                    .error(R.mipmap.ic_launcher)
                    .into(ivProd)

            }
        }
    }
}