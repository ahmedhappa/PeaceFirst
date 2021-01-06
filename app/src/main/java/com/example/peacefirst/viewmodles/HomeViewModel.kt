package com.example.peacefirst.viewmodles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.base.BaseViewModel
import com.example.peacefirst.models.response.ChildrenResponse
import com.example.peacefirst.base.Result
import com.example.peacefirst.models.ModelEnums
import com.example.peacefirst.models.request.ChildrenRequest
import com.example.peacefirst.repos.HomeRepo


class HomeViewModel : BaseViewModel() {
    private val homeRepo = HomeRepo()
    private val _childrenMutableLiveData =
        MutableLiveData<Result<BaseResponse<MutableList<ChildrenResponse>>>>()
    var childrenList: MutableList<ChildrenResponse> = mutableListOf()
    val childrenLiveData: LiveData<Result<BaseResponse<MutableList<ChildrenResponse>>>>
        get() = _childrenMutableLiveData
    private var childRequestFilters = ChildrenRequest()
    private val _childrenRequestMLD = MutableLiveData<ChildrenRequest>()
    val childrenRequestLD: LiveData<ChildrenRequest>
        get() = _childrenRequestMLD

    fun getAllChildren() {
        val requestPage: Int = (childrenList.size / 20) + 1
        callApi(_childrenMutableLiveData) {
            val result = homeRepo.getAllChildren(childRequestFilters, requestPage)
            result.data?.let {
                if (it.isNotEmpty())
                    childrenList.addAll(it)
            }
            result
        }
    }


    fun resetFilters() {
        childrenList.clear()
        childRequestFilters = ChildrenRequest()
        _childrenRequestMLD.value = childRequestFilters
        getAllChildren()
    }

    fun setNewFilters(childrenRequest: ChildrenRequest) {
        childrenList.clear()
        childRequestFilters = childrenRequest
        _childrenRequestMLD.value = childRequestFilters
        getAllChildren()
    }

    fun clearReportTypeFilter() {
        childRequestFilters.reportType = null
        setNewFilters(childRequestFilters)
    }

    fun clearGenderFilter() {
        childRequestFilters.gender = null
        setNewFilters(childRequestFilters)
    }

    fun clearAgeFilter() {
        childRequestFilters.minAge = null
        childRequestFilters.maxAge = null
        setNewFilters(childRequestFilters)
    }

    fun clearHeightFilter() {
        childRequestFilters.minHeight = null
        childRequestFilters.maxHeight = null
        setNewFilters(childRequestFilters)
    }

    fun clearSkinColorFilter() {
        childRequestFilters.skinColor = null
        setNewFilters(childRequestFilters)
    }

    fun clearHairColorFilter() {
        childRequestFilters.hairColor = null
        setNewFilters(childRequestFilters)
    }

    fun clearEyeColorFilter() {
        childRequestFilters.eyeColor = null
        setNewFilters(childRequestFilters)
    }

}