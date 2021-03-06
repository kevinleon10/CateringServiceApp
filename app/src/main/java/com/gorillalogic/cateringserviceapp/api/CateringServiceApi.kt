package com.gorillalogic.cateringserviceapp.api

import com.gorillalogic.cateringserviceapp.model.FeaturedCateringService
import com.gorillalogic.cateringserviceapp.model.UpcomingOrder
import io.reactivex.Single
import retrofit2.http.GET

interface CateringServiceApi {

    @GET("kevinleon10/CateringServiceApp/develop/featuredCateringServices.json")
    fun getFeaturedCateringServices(): Single<List<FeaturedCateringService>>

    @GET("kevinleon10/CateringServiceApp/develop/upcomingOrders.json")
    fun getUpcomingOrders(): Single<List<UpcomingOrder>>

}