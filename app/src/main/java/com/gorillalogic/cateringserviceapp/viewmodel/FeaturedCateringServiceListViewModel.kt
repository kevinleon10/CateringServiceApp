package com.gorillalogic.cateringserviceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillalogic.cateringserviceapp.model.FeaturedCateringService
import com.gorillalogic.cateringserviceapp.model.FeaturedCateringServiceApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeaturedCateringServiceListViewModel: ViewModel() {

    val featuredCateringServices by lazy { MutableLiveData<List<FeaturedCateringService>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()
    private val apiService = FeaturedCateringServiceApiService()

    fun refresh() {
        getCateringServices()
    }

    private fun getCateringServices() {
        disposable.add(
            apiService.getFeaturedCateringServices()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<FeaturedCateringService>>() {
                    override fun onSuccess(t: List<FeaturedCateringService>) {
                        loadError.value = false
                        featuredCateringServices.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadError.value = true
                        featuredCateringServices.value = null
                        loading.value = false
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}