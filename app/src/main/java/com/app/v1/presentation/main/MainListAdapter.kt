package com.app.v1.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.v1.R
import com.app.v1.common.Constants
import com.app.v1.data.MainItemDto
import com.app.v1.databinding.ItemMainListRelatedBinding
import com.app.v1.presentation.main.MainListAdapter.BaseBindingViewHolder
import com.app.v1.utils.LogUtils


class MainListAdapter(val context: Context) :
    RecyclerView.Adapter<BaseBindingViewHolder>() {
    private val TAG: String = LogUtils.makeLogTag(this.javaClass)

    val items =  mutableListOf<MainItemDto>()

    private var viewType: Constants.VIEW_TYPE = Constants.VIEW_TYPE.VIEW_TYPE_SUMMARY

    fun <T> updateData(list: List<T>?) {
        list?.let {
            items.clear()
            items.addAll(it)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
        if (position == 0) Constants.VIEW_TYPE.VIEW_TYPE_SUMMARY.value
        else Constants.VIEW_TYPE.VIEW_TYPE_SUMMARY.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return when (viewType) {
            Constants.VIEW_TYPE.VIEW_TYPE_SUMMARY.value -> {
                val binding = DataBindingUtil.inflate<ItemMainListRelatedBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_main_list_related,
                    parent,
                    false
                )
                return BaseBindingViewHolder(binding)
            }
            Constants.VIEW_TYPE.VIEW_TYPE_RELATED.value -> {
                val binding = DataBindingUtil.inflate<ItemMainListRelatedBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_main_list_related,
                    parent,
                    false
                )
                return BaseBindingViewHolder(binding)
            }

            else -> {
                val binding = DataBindingUtil.inflate<ItemMainListRelatedBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_main_list_related,
                    parent,
                    false
                )
                return BaseBindingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        LogUtils.d("Test", "[onBindViewHolder] viewType : " + viewType)
        with(holder.binding) {
            val item = items[position]
            LogUtils.e("Test", "[MainRelatedListDto] : "+item)
            when (position) {
                0 -> {
                    val binding = holder.binding as ItemMainListRelatedBinding

//                    binding.data = item
                }
                else -> {
                    val binding = holder.binding as ItemMainListRelatedBinding
//                    binding.data = item
                }

            }
        }
    }

    inner class BaseBindingViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

}