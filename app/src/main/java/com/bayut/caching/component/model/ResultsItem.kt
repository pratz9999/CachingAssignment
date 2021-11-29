package com.bayut.caching.component.model

import android.os.Parcelable
import com.bayut.caching.utility.CalendarHelper
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultsItem(

	@Json(name="uid")
	val uid: String? = null,

	@Json(name="price")
	val price: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="image_ids")
	val imageIds: List<String?>? = null,

	@Json(name="image_urls")
	val imageUrls: List<String?>? = null,

	@Json(name="image_urls_thumbnails")
	val imageUrlsThumbnails: List<String?>? = null
) : Parcelable {
	fun getCreateDate () : String{
		return CalendarHelper.getConverted(createdAt, CalendarHelper.DATE_DEFAULT_FORMAT, CalendarHelper.INPUT_DATE_FORMAT)
	}
}