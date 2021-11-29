package com.bayut.caching.service

import com.bayut.caching.component.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 *  REST API access points
 */
interface ApiService {

    companion object {
        const val ENDPOINT = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/"
        const val URL_IMAGE = "default/dynamodb-writer"
    }

    @GET(URL_IMAGE)
    suspend fun getImages(): Response<DataResponse>

}