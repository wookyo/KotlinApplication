package com.app.v1.data

import com.google.gson.annotations.SerializedName

data class TitlesDto constructor(
    @SerializedName("canonical")
    var canonical: String? = null,

    @SerializedName("normalized")
    var normalized: String? = null,

    @SerializedName("display")
    var display: String? = null

    ){
}