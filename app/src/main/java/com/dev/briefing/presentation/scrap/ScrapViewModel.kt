package com.dev.briefing.presentation.scrap

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.briefing.data.NewsDetail
import com.dev.briefing.data.model.GoogleRequest
import com.dev.briefing.data.model.ScrapResponse
import com.dev.briefing.data.model.SetScrapRequest
import com.dev.briefing.data.respository.AuthRepository
import com.dev.briefing.data.respository.ScrapRepository
import com.dev.briefing.util.SERVER_TAG
import com.dev.briefing.util.SharedPreferenceHelper
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class ScrapViewModel(private val repository: ScrapRepository) : ViewModel() {

    private val _scrapList: MutableLiveData<MutableList<ScrapResponse>> =
        MutableLiveData<MutableList<ScrapResponse>>(mutableListOf())
    val scrapList: LiveData<MutableList<ScrapResponse>>
        get() = _scrapList

    init {
        getScrapData()
    }

    /**
     * 스크랩한 기사들을 가져오는 메소드
     * [_scraList] 업데이트
     */
    fun getScrapData() {
        viewModelScope.launch {
            try {
                val response = repository.getScrap(
                    memberId = 0
                )
                Log.d(SERVER_TAG, "통신 끝")

                response.result.forEach { scrap ->
                    _scrapList.value?.add(scrap)
                }
                Log.d(SERVER_TAG, response.toString())
                Log.d(SERVER_TAG, "메소드 끝")
            } catch (e: Throwable) {
                Log.d(SERVER_TAG, e.toString())
            }
        }
    }
    //TODO: 스크랩한 api 결과에 따른 분기처리 혹은 return 값 수정 필요
    fun setScrap() {
        viewModelScope.launch {
            try {
                val response = repository.setScrap(
                    memberInfo = SetScrapRequest(
                        memberId = 0,
                        briefingId = 0
                    )
                )
                Log.d(SERVER_TAG, "통신 끝")
                Log.d(SERVER_TAG, response.toString())
                Log.d(SERVER_TAG, "메소드 끝")
            } catch (e: Throwable) {
                Log.d(SERVER_TAG, e.toString())
            }
        }
    }
    //TODO: 스크랩한 api 결과에 따른 분기처리 혹은 return 값 수정 필요
    fun setUnScrap() {
        viewModelScope.launch {
            try {
                val response = repository.unScrap(
                    scrapId = 0
                )
                Log.d(SERVER_TAG, "통신 끝")
                Log.d(SERVER_TAG, response.toString())
                Log.d(SERVER_TAG, "메소드 끝")
            } catch (e: Throwable) {
                Log.d(SERVER_TAG, e.toString())
            }
        }
    }
}