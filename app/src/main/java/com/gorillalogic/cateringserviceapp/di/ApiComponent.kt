package com.gorillalogic.cateringserviceapp.di

import com.gorillalogic.cateringserviceapp.api.CateringServiceApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CateringServiceApiService)
}