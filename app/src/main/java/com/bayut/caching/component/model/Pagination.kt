package com.bayut.caching.component.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pagination(

    @Json(name = "key")
    val key: Int? = null
) : Parcelable