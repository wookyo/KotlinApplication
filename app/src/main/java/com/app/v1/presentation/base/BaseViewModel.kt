package com.app.v1.presentation.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val isShowLoading = ObservableField<Boolean>()

    fun showLoading() {
        isShowLoading.set(true)
    }

    fun hideLoading() {
        isShowLoading.set(false)
    }
}