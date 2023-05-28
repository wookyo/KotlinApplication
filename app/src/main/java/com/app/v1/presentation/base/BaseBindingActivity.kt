package com.app.v1.presentation.base

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

abstract class BaseBindingActivity<T : ViewDataBinding> : FragmentActivity(){

    private var _binding: T? = null
    protected val binding get() = _binding!!

    abstract fun initData()

    abstract fun getLayoutRes(): Int

    abstract fun initView(viewDataBinding: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        _binding = DataBindingUtil.setContentView(this, getLayoutRes())
        initView(binding)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}


