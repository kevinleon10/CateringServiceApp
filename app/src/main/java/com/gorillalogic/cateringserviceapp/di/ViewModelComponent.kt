package com.gorillalogic.cateringserviceapp.di

import com.gorillalogic.cateringserviceapp.viewmodel.FeaturedCateringServicesViewModel
import com.gorillalogic.cateringserviceapp.viewmodel.UpcomingOrdersViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: FeaturedCateringServicesViewModel)

    fun inject(viewModel: UpcomingOrdersViewModel)
}