package com.gorillalogic.cateringserviceapp.model

import java.io.Serializable

data class Menu(
    val foodTimes: List<FoodTime>?
) : Serializable