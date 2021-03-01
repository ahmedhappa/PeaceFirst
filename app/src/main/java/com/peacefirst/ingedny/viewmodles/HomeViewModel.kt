package com.peacefirst.ingedny.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.peacefirst.ingedny.apputils.Extensions.notifyObserver
import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.base.BaseViewModel
import com.peacefirst.ingedny.models.response.ChildrenResponse
import com.peacefirst.ingedny.base.Result
import com.peacefirst.ingedny.models.request.ChildrenRequest
import com.peacefirst.ingedny.repos.HomeRepo


class HomeViewModel : BaseViewModel() {
    private val pageSize = 20
    private val homeRepo = HomeRepo()
    private val _childrenMutableLiveData =
        MutableLiveData<Result<BaseResponse<MutableList<ChildrenResponse>>>>()
    val childrenLiveData: LiveData<Result<BaseResponse<MutableList<ChildrenResponse>>>>
        get() = _childrenMutableLiveData

    private val _childrenListMLD = MutableLiveData<MutableList<ChildrenResponse>>()
    val childrenListLD: LiveData<MutableList<ChildrenResponse>>
        get() = _childrenListMLD

    private val _childrenRequestMLD = MutableLiveData<ChildrenRequest>()
    val childrenRequestLD: LiveData<ChildrenRequest>
        get() = _childrenRequestMLD

    var childrenListSize = 0

    init {
        _childrenListMLD.value = mutableListOf()
        _childrenRequestMLD.value = ChildrenRequest()
        getAllChildren()
    }

    fun getAllChildren() {
        val requestPage: Int = (_childrenListMLD.value!!.size / pageSize) + 1
        callApi(_childrenMutableLiveData) {
            val result = homeRepo.getAllChildren(_childrenRequestMLD.value!!, requestPage)
            result.data?.let {
                if (it.isNotEmpty()) {
                    _childrenListMLD.value?.addAll(it)
                    childrenListSize = _childrenListMLD.value!!.size
                    _childrenListMLD.notifyObserver()
                }
            }
            result
        }
    }


    fun resetFilters() {
        childrenListSize = 0
        _childrenListMLD.value = mutableListOf()
        _childrenRequestMLD.value = ChildrenRequest()
        getAllChildren()
    }

    fun setNewFilters(childrenRequest: ChildrenRequest) {
        childrenListSize = 0
        _childrenListMLD.value = mutableListOf()
        _childrenRequestMLD.value = childrenRequest
        getAllChildren()
    }

    fun clearReportTypeFilter() {
        _childrenRequestMLD.value?.reportType = null
        setNewFilters(_childrenRequestMLD.value!!)
    }

    fun clearGenderFilter() {
        _childrenRequestMLD.value?.gender = null
        setNewFilters(_childrenRequestMLD.value!!)
    }

    fun clearAgeFilter() {
        _childrenRequestMLD.value?.minAge = null
        _childrenRequestMLD.value?.maxAge = null
        setNewFilters(_childrenRequestMLD.value!!)
    }

    fun clearHeightFilter() {
        _childrenRequestMLD.value?.minHeight = null
        _childrenRequestMLD.value?.maxHeight = null
        setNewFilters(_childrenRequestMLD.value!!)
    }

    fun clearSkinColorFilter() {
        _childrenRequestMLD.value?.skinColor = null
        setNewFilters(_childrenRequestMLD.value!!)
    }

    fun clearHairColorFilter() {
        _childrenRequestMLD.value?.hairColor = null
        setNewFilters(_childrenRequestMLD.value!!)
    }

    fun clearEyeColorFilter() {
        _childrenRequestMLD.value?.eyeColor = null
        setNewFilters(_childrenRequestMLD.value!!)
    }

    fun canLoadMoreData(): Boolean {
        return (childrenListSize % pageSize) == 0
    }
}