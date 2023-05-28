package com.app.v1.data

import com.google.gson.annotations.SerializedName

data class NameSpacedDto constructor(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("text")
    var text: String? = null,

){
}