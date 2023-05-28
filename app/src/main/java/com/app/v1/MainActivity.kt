package com.app.v1

import com.app.v1.databinding.ActivityMainBinding
import com.app.v1.presentation.base.BaseBindingActivity
import com.app.v1.presentation.main.MainListFragment
import com.app.v1.utils.FragmentUtils

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    private var searchText = "google"

    override fun initData() {
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView(viewDataBinding: ActivityMainBinding) {
        showMainListFragment()
    }

    private fun showMainListFragment(){
        binding.let {
            val fragment = supportFragmentManager.findFragmentByTag(MainListFragment.TAG)
            fragment?.let {
                if(fragment is MainListFragment){
                    fragment.updateData(searchText)
                }

            }?: run {
                FragmentUtils.replaceFragment(
                    supportFragmentManager,
                    MainListFragment.newInstance("main", searchText)
                    , it.container.id,
                    MainListFragment.TAG)
            }
        }
    }

}