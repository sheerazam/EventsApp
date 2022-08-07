package com.example.andleatask.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Children(
    @SerialName("children")
    val children: List<Children>? = mutableListOf(),
    @SerialName("events")
    val events: List<Event>? = mutableListOf(),
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
)