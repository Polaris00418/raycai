package com.raycai.fluffie.ui.home.product.claims

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ClaimsViewModel() : ViewModel() {

    private val TAG = ClaimsViewModel::class.java.simpleName

    val isShowingMore = MutableLiveData<Boolean>()

    fun onShowMoreClicked() {
        isShowingMore.postValue(!isShowingMore.value!!)
    }

    fun initData() {
        isShowingMore.postValue(false)
    }


}