package com.app.v1.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.v1.data.MainRelatedDto.MainRelatedListDto


object BindingUtils{
    @JvmStatic
    @BindingAdapter("setItemText")
    fun setItemText(view:TextView, inputText: String?){
        inputText?.let {
            view.text = it
        }
    }

    @JvmStatic
    @BindingAdapter("setRelatedItemThumbnail")
    fun setRelatedItemThumbnail(view:ImageView, data: MainRelatedListDto?){
        data?.thumbnail?.source?.let {
            ThumbnailAsyncTask(view, it).execute()
        }
    }

}