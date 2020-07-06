package com.gorillalogic.cateringserviceapp.model

import java.io.Serializable
import java.util.*

class CateringService(
    val name: String?,
    val type: String?,
    val imageUrls: List<String>?,
    val pricePerGuest: Int?,
    val description: String?,
    val preparationTime: String?,
    val minimumGuests: Int?,
    val maximumGuests: Int?,
    val priceRange: String?,
    val menu: Menu?,
    val timeAvailability: List<String>?
) : Serializable