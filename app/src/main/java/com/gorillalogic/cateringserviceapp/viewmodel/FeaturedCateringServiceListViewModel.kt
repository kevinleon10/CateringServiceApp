package com.gorillalogic.cateringserviceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillalogic.cateringserviceapp.model.FeaturedCateringService

class FeaturedCateringServiceListViewModel: ViewModel() {

    val featuredCateringServiceList by lazy { MutableLiveData<List<FeaturedCateringService>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    fun refresh() {
        getCateringServiceList()
    }

    private fun getCateringServiceList() {
        val a = FeaturedCateringService("Hire for this week", null)
        val b = FeaturedCateringService("Most popular", null)

        val featuredCateringServiceArrayList = arrayListOf(a, b)
        featuredCateringServiceList.value = featuredCateringServiceArrayList
        loadError.value = false
        loading.value = false
    }
}