package com.gorillalogic.cateringserviceapp.viewmodel

import android.view.View
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
    val upcomingOrdersVisibility by lazy { MutableLiveData<Int>() }
    val errorVisibility by lazy { MutableLiveData<Int>() }
    val loadingVisibility by lazy { MutableLiveData<Int>() }

    private val disposable = CompositeDisposable()

    fun refresh() {
        errorVisibility.value = View.GONE
        upcomingOrdersVisibility.value = View.GONE
        loadingVisibility.value = View.VISIBLE
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
                        upcomingOrdersVisibility.value = View.VISIBLE
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