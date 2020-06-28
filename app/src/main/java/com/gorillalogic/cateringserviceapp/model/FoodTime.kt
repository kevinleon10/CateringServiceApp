package com.gorillalogic.cateringserviceapp.model

import java.io.Serializable

data class FoodTime(
    val name: String?,
    val saucers: List<Saucer>?
) : Serializable