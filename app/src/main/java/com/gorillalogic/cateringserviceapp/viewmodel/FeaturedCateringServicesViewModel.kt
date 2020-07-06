package com.gorillalogic.cateringserviceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillalogic.cateringserviceapp.model.FeaturedCateringService
import com.gorillalogic.cateringserviceapp.model.CateringServiceApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeaturedCateringServicesViewModel: ViewModel() {

    val featuredCateringServices by lazy { MutableLiveData<List<FeaturedCateringService>>() }
    val errorVisible by lazy { MutableLiveData<Boolean>() }
    val loadingVisible by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()

    fun refresh() {
        errorVisible.value = false
        featuredCateringServices.value = null
        loadingVisible.value = true
        getCateringServices()
    }

    private fun getCateringServices() {
        disposable.add(
            CateringServiceApiService.instance.getFeaturedCateringServices()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<FeaturedCateringService>>() {
                    override fun onSuccess(t: List<FeaturedCateringService>) {
                        featuredCateringServices.value = t
                        loadingVisible.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        errorVisible.value = true
                        loadingVisible.value = false
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}