package com.example.yuefenginterviewproject.ui.home.tabfragment

import androidx.lifecycle.AndroidViewModel
import com.example.yuefenginterviewproject.BaseRecyclerViewAdapter
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.SubHotEntity
import com.example.yuefenginterviewproject.data.model.HomeViewModel
import com.example.yuefenginterviewproject.databinding.ItemHomeBannerAdapterBinding

class HomeAdBannerItemAdapter : BaseRecyclerViewAdapter<SubHotEntity, ItemHomeBannerAdapterBinding>() {

    override val resId: Int = R.layout.item_home_banner_adapter

    override fun bindData(
        holder: BaseViewHolder<ItemHomeBannerAdapterBinding>,
        entity: SubHotEntity,
        viewModel: AndroidViewModel
    ) {
        holder.binding?.homeModel = viewModel as HomeViewModel
        holder.binding?.bannerEntity = entity
        holder.binding?.executePendingBindings()
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemHomeBannerAdapterBinding>,
        position: Int
    ) {
        val entity = itemList[position]
        bindData(holder, entity, itemViewModel)
    }
}