package com.gorillalogic.cateringserviceapp.model

import io.reactivex.Single
import retrofit2.http.GET

interface FeaturedCateringServiceApi {

    @GET("kevinleon10/CateringServiceApp/develop/featuredCateringServiceList.json")
    fun getFeaturedCateringServices(): Single<List<FeaturedCateringService>>

}