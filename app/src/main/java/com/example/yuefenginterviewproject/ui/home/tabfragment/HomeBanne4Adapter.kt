package com.example.yuefenginterviewproject.ui.home.tabfragment

import androidx.lifecycle.AndroidViewModel
import com.example.yuefenginterviewproject.BaseRecyclerViewAdapter
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.SubHotEntity
import com.example.yuefenginterviewproject.data.model.HomeViewModel
import com.example.yuefenginterviewproject.databinding.ItemHomeBanner4AdapterBinding

class HomeBanne4Adapter : BaseRecyclerViewAdapter<SubHotEntity, ItemHomeBanner4AdapterBinding>() {

    override val resId: Int = R.layout.item_home_banner4_adapter

    override fun bindData(
        holder: BaseViewHolder<ItemHomeBanner4AdapterBinding>,
        entity: SubHotEntity,
        viewModel: AndroidViewModel
    ) {
        holder.binding?.homeModel = viewModel as HomeViewModel
        holder.binding?.bannerEntity = entity
        holder.binding?.executePendingBindings()
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemHomeBanner4AdapterBinding>,
        position: Int
    ) {
        val entity = itemList[position]
        bindData(holder, entity, itemViewModel)
    }
}