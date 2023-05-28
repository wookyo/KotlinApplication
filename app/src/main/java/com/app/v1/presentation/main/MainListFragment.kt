package com.app.v1.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.v1.R
import com.app.v1.data.MainRelatedDto.MainRelatedListDto
import com.app.v1.databinding.FragmentMainListBinding
import com.app.v1.presentation.base.BaseBindingFragment
import com.app.v1.utils.LogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainListFragment : BaseBindingFragment<FragmentMainListBinding>(){
    private val TAG: String = LogUtils.makeLogTag(this.javaClass)

    private var search_text = "google"

    private lateinit var mAdapter: MainListAdapter

    private val viewModel: MainListModel by lazy {
        ViewModelProvider(this).get(MainListModel::class.java)
    }

    companion object {
        val TAG: String = MainListFragment::class.java.simpleName
        const val PARAM_SEARCH_TEXT = "parm_search_text"

        fun newInstance(viewType: String, search: String): MainListFragment {
            return MainListFragment().apply {
                arguments = Bundle().apply {
                    putString(PARAM_SEARCH_TEXT, search)
                }
            }
        }
    }

    override fun initData() {
        arguments?.let {
            search_text = it.getString(PARAM_SEARCH_TEXT, "")
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_main_list
    }

    override fun initView(viewDataBinding: FragmentMainListBinding) {
        with(viewDataBinding) {
            viewModel.run {

//                //연관 검색 API
//                mainRelatedDto.observe(viewLifecycleOwner) { result ->
//                    result.data_List?.let {
//                        updateList(it)
//                    }
//                }
            }
            mainListView.run {
                mAdapter = MainListAdapter(context)
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
            swipeToRefresh.run {
                setOnRefreshListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        swipeToRefresh.isRefreshing = false // This stops refreshing
                        requestMainData(search_text)
                    }
                }
            }
        }
        requestMainData(search_text)
    }


    fun requestMainData(search:String){
        if(search.isNullOrEmpty()) return

        search_text = search

        //연관 검색 API
        viewModel.requestMainListData(search_text).observe(viewLifecycleOwner){ response ->
            response.result?.let { result ->
                result.data_List?.let {
                    updateList(it)
                }
            }
        }
    }

    fun updateData(input:String) {
        search_text = input
        requestMainData(search_text)
    }

    fun updateList(list: List<MainRelatedListDto>?) {
        if(list.isNullOrEmpty()) return

        activity?.let {
            it.runOnUiThread {
                mAdapter.run {
                    updateData(list)
                    notifyDataSetChanged()
                }
            }
        }
    }

}