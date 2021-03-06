package com.peacefirst.ingedny.viewmodles

import com.peacefirst.ingedny.base.BaseViewModel
import com.peacefirst.ingedny.models.request.ChildrenRequest

class FilterViewModel : BaseViewModel() {
    var childrenRequest = resetData()

    fun resetData(): ChildrenRequest = ChildrenRequest(
        null,
        null,
        0, null,
        0, null,
        null, null, null
    )

    fun isFilterInserted(): Boolean {
        childrenRequest.apply {
            return reportType != null ||
                    gender != null ||
                    maxAge != null ||
                    maxHeight != null ||
                    skinColor != null || hairColor != null || eyeColor != null
        }
    }
}