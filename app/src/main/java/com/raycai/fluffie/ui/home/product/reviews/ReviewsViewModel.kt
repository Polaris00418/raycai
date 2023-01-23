package com.raycai.fluffie.ui.home.product.reviews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raycai.fluffie.data.model.Product
import com.raycai.fluffie.data.model.Review
import com.raycai.fluffie.data.model.UserReview
import com.raycai.fluffie.ui.home.product.claims.ClaimsViewModel

class ReviewsViewModel() : ViewModel() {

    private val TAG = ClaimsViewModel::class.java.simpleName

    enum class FilterType {
        ALL, TRENDING, VIDEO, AUDIO
    }

    val selectedFilterType = MutableLiveData(FilterType.ALL)
    val reviewChanged = MutableLiveData<Boolean>()

    var reviews: ArrayList<UserReview> = ArrayList()

    fun onFilterSelected(ft: FilterType){
        if (ft != selectedFilterType.value){
            selectedFilterType.postValue(ft)
        }
    }

    fun initData() {
        selectedFilterType.postValue(FilterType.ALL)

        reviews.add(UserReview())
        reviews.add(UserReview())
        reviews.add(UserReview())
        reviews.add(UserReview())
        reviews.add(UserReview())
        reviews.add(UserReview())
        reviews.add(UserReview())
        reviews.add(UserReview())
        reviews.add(UserReview())
        reviews.add(UserReview())

        reviewChanged.postValue(true)
    }


}