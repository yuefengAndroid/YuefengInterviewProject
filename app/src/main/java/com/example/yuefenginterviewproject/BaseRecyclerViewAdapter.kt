package com.example.yuefenginterviewproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter <T, VB: ViewDataBinding>: RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<VB>>() {
    abstract val resId: Int
    var itemList: MutableList<T> = mutableListOf()
    lateinit var itemViewModel: AndroidViewModel
    abstract fun bindData(holder: BaseViewHolder<VB>, entity: T, viewModel: AndroidViewModel)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: VB = DataBindingUtil.inflate(layoutInflater, resId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val entity = itemList[position]
        bindData(holder, entity, itemViewModel)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(entityList: MutableList<T>, itemViewModel: AndroidViewModel){
        this.itemViewModel = itemViewModel
        val isEquals = this.itemList.size == entityList.size && this.itemList.containsAll(entityList)
        if (!isEquals){
            this.itemList.clear()
            this.itemList.addAll(entityList)
            notifyDataSetChanged()
        }
    }

    class BaseViewHolder<VB: ViewDataBinding>(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        val binding = DataBindingUtil.bind<VB>(binding.root)
    }
}