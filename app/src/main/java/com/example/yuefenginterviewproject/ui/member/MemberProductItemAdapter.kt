package com.example.yuefenginterviewproject.ui.member

import android.graphics.Rect
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.yuefenginterviewproject.BaseRecyclerViewAdapter
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.ProductsEntity
import com.example.yuefenginterviewproject.data.model.MemberViewModel
import com.example.yuefenginterviewproject.databinding.RecyclerviewProductsItemsBinding

class MemberProductItemAdapter :
    BaseRecyclerViewAdapter<ProductsEntity,RecyclerviewProductsItemsBinding>() {
    override val resId: Int = R.layout.recyclerview_products_items


    override fun bindData(
        holder: BaseViewHolder<RecyclerviewProductsItemsBinding>,
        entity: ProductsEntity,
        viewModel: AndroidViewModel
    ) {
        holder.binding?.productEntity = entity
        holder.binding?.memberAdapterItem = viewModel as MemberViewModel
        holder.binding?.executePendingBindings()
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<RecyclerviewProductsItemsBinding>,
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
