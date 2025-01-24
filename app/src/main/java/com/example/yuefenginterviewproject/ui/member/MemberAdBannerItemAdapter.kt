package com.example.yuefenginterviewproject.ui.member

import androidx.lifecycle.AndroidViewModel
import com.example.yuefenginterviewproject.BaseRecyclerViewAdapter
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.StoreCouponEntity
import com.example.yuefenginterviewproject.data.model.MemberViewModel
import com.example.yuefenginterviewproject.databinding.RecyclerviewActivityBannerItemBinding

class MemberAdBannerItemAdapter : BaseRecyclerViewAdapter<StoreCouponEntity, RecyclerviewActivityBannerItemBinding>() {

    override val resId: Int = R.layout.recyclerview_activity_banner_item

    override fun bindData(
        holder: BaseViewHolder<RecyclerviewActivityBannerItemBinding>,
        entity: StoreCouponEntity,
        viewModel: AndroidViewModel
    ) {
        holder.binding?.memberModel = viewModel as MemberViewModel
        holder.binding?.bannerEntity = entity
        holder.binding?.executePendingBindings()
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<RecyclerviewActivityBannerItemBinding>,
        position: Int
    ) {
        val entity = itemList[position]
        bindData(holder, entity, itemViewModel)
    }
}