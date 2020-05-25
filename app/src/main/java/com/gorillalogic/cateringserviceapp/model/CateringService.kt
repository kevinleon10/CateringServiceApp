package com.gorillalogic.cateringserviceapp.model

import java.util.*

class CateringService(
    val name: String?,
    val type: String?,
    val imageURLList: List<String>?,
    val pricePerGuest: Float?,
    val description: String?,
    val preparationTime: String?,
    val minimumGuests: Int?,
    val maximumGuests: Int?,
    val priceRange: String?,
    val menu: Menu?,
    val timeAvailability: List<Date>?
)