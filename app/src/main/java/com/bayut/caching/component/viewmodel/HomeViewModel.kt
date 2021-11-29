package com.bayut.caching.component.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.bayut.caching.service.Result
import com.bayut.caching.component.DashboardRepository
import com.bayut.caching.component.model.DataResponse

/**
 * The ViewModel for Home.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(repository: DashboardRepository) : ViewModel() {
    var getImages: () -> LiveData<Result<DataResponse>> =
        { repository.getImages() }
}