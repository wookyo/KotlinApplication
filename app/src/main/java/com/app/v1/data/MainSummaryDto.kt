package com.app.v1.data

import com.google.gson.annotations.SerializedName

data class MainSummaryDto (
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("displaytitle")
    var display_title: String? = null,

    @SerializedName("titles")
    var titles: TitlesDto? = null,

    @SerializedName("thumbnail")
    var thumbnail: thumbnailDto? = null,

    @SerializedName("originalimage")
    var originalimage: thumbnailDto? = null,

    @SerializedName("extract")
    var extract: String? = null,

    @SerializedName("extract_html")
    var extract_html: String? = null)