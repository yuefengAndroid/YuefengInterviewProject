package com.example.yuefenginterviewproject.ui.popularactivities.subview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.SubHotEntity

class SubEvent01ListAdapter (val frag: Fragment, var list: ArrayList<SubHotEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(frag.context).inflate(R.layout.item_sub_event, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(list[position])
    }

    private inner class ViewHolder(pContentView: View) : RecyclerView.ViewHolder(pContentView) {
        private val ivProd: ImageView = pContentView.findViewById(R.id.subItem01)

        fun bind(data: SubHotEntity) {
            if (frag.isAdded) {
                Glide.with(frag)
                    .load(data.ImgUrl)
                    .error(R.mipmap.ic_launcher)
                    .into(ivProd)

            }
        }
    }
}