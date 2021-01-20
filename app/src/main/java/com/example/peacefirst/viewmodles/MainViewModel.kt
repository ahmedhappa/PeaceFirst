package com.example.peacefirst.viewmodles

import androidx.lifecycle.MutableLiveData
import com.example.peacefirst.apputils.AppSharedPreference
import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.base.BaseViewModel
import com.example.peacefirst.base.Result
import com.example.peacefirst.models.response.SocialResponse
import com.example.peacefirst.repos.MainRepo

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