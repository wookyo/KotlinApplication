package com.app.v1.presentation.main

import androidx.lifecycle.MutableLiveData
import com.app.v1.common.Constants
import com.app.v1.data.BaseResponse
import com.app.v1.data.MainRelatedDto
import com.app.v1.domain.resository.MainListResository
import com.app.v1.presentation.base.BaseViewModel


class MainListModel: BaseViewModel(){
    private val TAG = BaseViewModel::class.java.simpleName!!

    val contentType: MutableLiveData<String> = MutableLiveData()
    val isMainListShowing : MutableLiveData<Boolean> = MutableLiveData(true)


    // 검색 상세 페이지

    // 요약 정보 API 결과
    val mainSummaryDto: MutableLiveData<Object> = MutableLiveData()

    //연관 검색 API data
    val mainRelatedDto: MutableLiveData<MainRelatedDto> = MutableLiveData()


    //연관 검색, 요약 정보 data 요청
    fun requestMainListData(searchText: String): MutableLiveData <BaseResponse<MainRelatedDto>>{
        val url_related = StringBuilder(Constants.URL.URL_RELATED.value).append(searchText).toString()
        return MainListResository.requestRelatedData(url_related)
    }

//    fun requestMainListData(searchText: String?) = viewModelScope.launch {
//
////        // 요약 정보 data 요청
////        val url_summary = StringBuilder(Constants.URL.URL_SUMMARY.value).append(searchText).toString()
////        URLConnectionAsyncTask(url_summary, mainRecommandListDto).execute()
//
//        // 연관 data 요청
//        val url_related = StringBuilder(Constants.URL.URL_RELATED.value).append(searchText).toString()
////        URLConnectionAsyncTask(url_related, mainRelatedDto).execute()
//
//
//    }





}