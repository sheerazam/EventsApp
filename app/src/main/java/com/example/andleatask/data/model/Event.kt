package com.example.andleatask.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("city")
    val city: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("dayOfWeek")
    val dayOfWeek: String? = null,
    @SerialName("distanceFromVenue")
    val distanceFromVenue: Double? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("price")
    val price: Int? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("venueName")
    val venueName: String? = null
)