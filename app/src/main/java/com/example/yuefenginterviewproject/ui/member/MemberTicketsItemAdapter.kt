package com.example.yuefenginterviewproject.ui.member

import android.graphics.Rect
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.yuefenginterviewproject.BaseRecyclerViewAdapter
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.NavbarEntity
import com.example.yuefenginterviewproject.data.model.MemberViewModel
import com.example.yuefenginterviewproject.databinding.RecyclerviewTicketsItemsBinding

class MemberTicketsItemAdapter :
    BaseRecyclerViewAdapter<NavbarEntity, RecyclerviewTicketsItemsBinding>() {
    override val resId: Int = R.layout.recyclerview_tickets_items


    override fun bindData(
        holder: BaseViewHolder<RecyclerviewTicketsItemsBinding>,
        entity: NavbarEntity,
        viewModel: AndroidViewModel
    ) {
        holder.binding?.navbarEntity = entity
        holder.binding?.memberAdapterItem = viewModel as MemberViewModel
        holder.binding?.executePendingBindings()
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<RecyclerviewTicketsItemsBinding>,
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
            outRect.left = columnSpacing
            outRect.right = columnSpacing
        }
    }

}