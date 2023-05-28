package com.app.v1.data

import com.google.gson.annotations.SerializedName

data class thumbnailDto constructor(
    @SerializedName("source")
    var source: String? = null,

    @SerializedName("width")
    var width: Int? = 0,

    @SerializedName("display")
    var display: String? = null,

    ){
}