package com.bayut.caching.component.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataResponse(

	@Json(name="pagination")
	val pagination: Pagination? = null,

	@Json(name="results")
	val results: List<ResultsItem?>? = null
):Parcelable