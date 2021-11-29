package com.bayut.caching.component

import com.bayut.caching.service.ApiService
import com.bayut.caching.service.BaseDataSource
import javax.inject.Inject

class DashboardRemoteDataSource @Inject constructor(private val service: ApiService) :
    BaseDataSource() {

    suspend fun getImages() =
        getResult {
            service.getImages()
        }


}
