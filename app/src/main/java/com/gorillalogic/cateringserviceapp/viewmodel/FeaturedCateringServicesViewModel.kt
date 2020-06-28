package com.gorillalogic.cateringserviceapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillalogic.cateringserviceapp.model.FeaturedCateringService
import com.gorillalogic.cateringserviceapp.model.FeaturedCateringServiceApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeaturedCateringServicesViewModel: ViewModel() {

    val featuredCateringServices by lazy { MutableLiveData<List<FeaturedCateringService>>() }
    val featuredCateringServicesVisibility by lazy { MutableLiveData<Int>() }
    val errorVisibility by lazy { MutableLiveData<Int>() }
    val loadingVisibility by lazy { MutableLiveData<Int>() }

    private val disposable = CompositeDisposable()
    private val apiService = FeaturedCateringServiceApiService()

    fun refresh() {
        errorVisibility.value = View.GONE
        featuredCateringServicesVisibility.value = View.GONE
        loadingVisibility.value = View.VISIBLE
        getCateringServices()
    }

    private fun getCateringServices() {
        disposable.add(
            apiService.getFeaturedCateringServices()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<FeaturedCateringService>>() {
                    override fun onSuccess(t: List<FeaturedCateringService>) {
                        featuredCateringServices.value = t
                        featuredCateringServicesVisibility.value = View.VISIBLE
                        loadingVisibility.value = View.GONE
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        errorVisibility.value = View.VISIBLE
                        loadingVisibility.value = View.GONE
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}