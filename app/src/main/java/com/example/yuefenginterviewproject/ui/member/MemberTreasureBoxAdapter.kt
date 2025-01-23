package com.example.yuefenginterviewproject.ui.member

import android.graphics.Rect
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.yuefenginterviewproject.BaseRecyclerViewAdapter
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.NavbarEntity
import com.example.yuefenginterviewproject.data.model.MemberViewModel
import com.example.yuefenginterviewproject.databinding.RecyclerviewTreasureboxItemsBinding

class MemberTreasureBoxAdapter :
    BaseRecyclerViewAdapter<NavbarEntity, RecyclerviewTreasureboxItemsBinding>() {
    override val resId: Int = R.layout.recyclerview_treasurebox_items


    override fun bindData(
        holder: BaseRecyclerViewAdapter.BaseViewHolder<RecyclerviewTreasureboxItemsBinding>,
        entity: NavbarEntity,
        viewModel: AndroidViewModel
    ) {
        holder.binding?.navbarEntity = entity
        holder.binding?.memberAdapterItem = viewModel as MemberViewModel
        holder.binding?.executePendingBindings()
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewAdapter.BaseViewHolder<RecyclerviewTreasureboxItemsBinding>,
        position: Int
    ) {
        val entity = itemList[position]
        bindData(holder, entity, itemViewModel)

    }

    class HomeNavbarItemDecoration(private val columnSpacing: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val spanCount = 4 // 每行 4 個項目
            val column = position % spanCount // 計算當前項目的列位置

            // 左間距
            outRect.left = if (column == 0) 0 else columnSpacing / 2
            // 右間距
            outRect.right = if (column == spanCount - 1) 0 else columnSpacing / 2
        }
    }

}