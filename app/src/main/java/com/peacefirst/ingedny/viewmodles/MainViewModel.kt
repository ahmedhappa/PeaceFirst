package com.peacefirst.ingedny.viewmodles

import androidx.lifecycle.MutableLiveData
import com.peacefirst.ingedny.apputils.AppSharedPreference
import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.base.BaseViewModel
import com.peacefirst.ingedny.base.Result
import com.peacefirst.ingedny.models.response.SocialResponse
import com.peacefirst.ingedny.repos.MainRepo

class MainViewModel : BaseViewModel() {
    private val mainRepo = MainRepo()
    private val _instructionsMLD = MutableLiveData<Result<BaseResponse<String>>>()
    private val _socialMLD = MutableLiveData<Result<BaseResponse<List<SocialResponse>>>>()
    fun getInstructions() {
        callApi(_instructionsMLD) {
            val result = mainRepo.getInstructions()
            AppSharedPreference.appInstructions = result.data
            result
        }
    }

    fun getSocials(){
        callApi(_socialMLD){
            val result = mainRepo.getSocials()
            AppSharedPreference.storeSocialDataList(result.data)
            result
        }
    }
}