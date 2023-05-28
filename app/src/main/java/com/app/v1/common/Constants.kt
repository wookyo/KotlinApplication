package com.app.v1.common

class Constants {
    enum class VIEW_TYPE(val value:Int){
        VIEW_TYPE_SUMMARY(100),
        VIEW_TYPE_RELATED(101)
    }

    enum class URL(val value:String){
        URL_SUMMARY("https://en.wikipedia.org/api/rest_v1/page/summary/"),
        URL_RELATED("https://en.wikipedia.org/api/rest_v1/page/related/")
    }
}