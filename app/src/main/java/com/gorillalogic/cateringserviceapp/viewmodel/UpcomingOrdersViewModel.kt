package com.gorillalogic.cateringserviceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillalogic.cateringserviceapp.model.CateringServiceApiService
import com.gorillalogic.cateringserviceapp.model.UpcomingOrder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UpcomingOrdersViewModel: ViewModel() {

    val upcomingOrders by lazy { MutableLiveData<List<UpcomingOrder>>() }
    val errorVisible by lazy { MutableLiveData<Boolean>() }
    val loadingVisible by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()

    fun refresh() {
        errorVisible.value = false
        loadingVisible.value = true
        upcomingOrders.value = null
        getUpcomingOrders()
    }

    private fun getUpcomingOrders() {
        disposable.add(
            CateringServiceApiService.instance.getUpcomingOrders()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<UpcomingOrder>>() {
                    override fun onSuccess(t: List<UpcomingOrder>) {
                        upcomingOrders.value = t
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