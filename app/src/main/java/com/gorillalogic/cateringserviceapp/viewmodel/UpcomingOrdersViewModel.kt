package com.gorillalogic.cateringserviceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillalogic.cateringserviceapp.di.DaggerViewModelComponent
import com.gorillalogic.cateringserviceapp.api.CateringServiceApiService
import com.gorillalogic.cateringserviceapp.model.UpcomingOrder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpcomingOrdersViewModel: ViewModel() {

    val upcomingOrders by lazy { MutableLiveData<List<UpcomingOrder>>() }
    val errorVisible by lazy { MutableLiveData<Boolean>() }
    val loadingVisible by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var cateringServiceApiService: CateringServiceApiService

    init {
        DaggerViewModelComponent.create().inject(this)
    }

    fun refresh() {
        errorVisible.value = false
        loadingVisible.value = true
        upcomingOrders.value = null
        getUpcomingOrders()
    }

    private fun getUpcomingOrders() {
        disposable.add(
            cateringServiceApiService.getUpcomingOrders()
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