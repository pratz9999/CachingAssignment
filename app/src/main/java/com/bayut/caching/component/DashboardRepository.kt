package com.bayut.caching.component

import com.bayut.caching.service.resultLiveData
import javax.inject.Inject

class DashboardRepository @Inject constructor(private val remoteSource: DashboardRemoteDataSource) {

    fun getImages() = resultLiveData(
        networkCall = { remoteSource.getImages() })
}