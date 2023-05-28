package com.app.v1.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MainRelatedDto(
    @SerializedName("pages")
    var data_List: List<MainRelatedListDto> = listOf()) {

    data class MainRelatedListDto (

        @SerializedName("ns")
        var ns: Int? = 0,

        @SerializedName("index")
        var index: Int? = 0,

        @SerializedName("type")
        var type: String? = null,

        @SerializedName("title")
        var title: String? = null,

        @SerializedName("namespace")
        var name_space: NameSpacedDto? = null,

        @SerializedName("titles")
        var titles: TitlesDto? = null,

        @SerializedName("extract")
        var extract: String? = null,

        @SerializedName("thumbnail")
        var thumbnail: thumbnailDto? = null)
}