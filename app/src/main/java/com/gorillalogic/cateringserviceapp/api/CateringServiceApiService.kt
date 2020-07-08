package com.gorillalogic.cateringserviceapp.api

import com.gorillalogic.cateringserviceapp.di.DaggerApiComponent
import com.gorillalogic.cateringserviceapp.model.FeaturedCateringService
import com.gorillalogic.cateringserviceapp.model.UpcomingOrder
import io.reactivex.Single
import javax.inject.Inject

class CateringServiceApiService {

    @Inject
    lateinit var api: CateringServiceApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getFeaturedCateringServices(): Single<List<FeaturedCateringService>> {
        return api.getFeaturedCateringServices()
    }

    fun getUpcomingOrders(): Single<List<UpcomingOrder>> {
        return api.getUpcomingOrders()
    }
}