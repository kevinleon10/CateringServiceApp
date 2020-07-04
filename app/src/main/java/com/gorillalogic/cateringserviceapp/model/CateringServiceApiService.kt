package com.gorillalogic.cateringserviceapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CateringServiceApiService {

    companion object {
        val instance = CateringServiceApiService()
    }

    private val BASE_URL = "https://raw.githubusercontent.com"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CateringServiceApi::class.java)

    fun getFeaturedCateringServices(): Single<List<FeaturedCateringService>> {
        return api.getFeaturedCateringServices()
    }

    fun getUpcomingOrders(): Single<List<UpcomingOrder>> {
        return api.getUpcomingOrders()
    }
}