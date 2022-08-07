package com.example.andleatask.ui.viewmodel

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.andleatask.data.model.Children
import com.example.andleatask.data.model.Event
import com.example.andleatask.exampleJson
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.json.Json

class ListScreenViewModel @AssistedInject constructor(
    @Assisted val initialState: ListScreenState,
) : MavericksViewModel<ListScreenState>(initialState = initialState) {

    init {
        setState {
            val data = Json.decodeFromString(Children.serializer(), exampleJson)
            copy(
                listEvents = getEventsRec(eventsResponse = data),
                listSearchedEvents = getEventsRec(eventsResponse = data)
            )
        }
    }

    fun getEventsRec(eventsResponse: Children): List<Event> {
        val listEvents = mutableListOf<Event>()
        eventsResponse.events?.forEach { event ->
            listEvents.add(event)
        }
        if (eventsResponse.children?.isNotEmpty() == true) {
            eventsResponse.children.forEach { child ->
                listEvents.addAll(getEventsRec(child))
            }
        }
        return listEvents
    }

    fun updateCity(city: String) {
        setState {
            copy(
                city = city,
            )
        }
    }

    fun updatePrice(price: String) {
        setState {
            copy(
                price = price,
            )
        }
    }

    fun searchEvents(text: String, price: String, listEvents: List<Event>) {
        val filteredEvents = when {
            text.isNotEmpty() && price.isNotEmpty() -> {
                listEvents.filter {
                    (it.price ?: 0) <= (price.toDoubleOrNull() ?: 0.0)
                            && it.city?.lowercase()?.contains(text.lowercase()) == true
                }
            }
            text.isNotEmpty() -> {
                searchEventsByText(text, listEvents)
            }
            price.isNotEmpty() -> {
                searchEventsByPrice(price, listEvents)
            }
            else -> {
                listEvents
            }
        }
        setState {
            copy(
                listSearchedEvents = filteredEvents
            )
        }
    }

    fun searchEventsByText(text: String, listEvents: List<Event>): List<Event> {
        val filteredByCity = if (text.isNotEmpty()) {
            listEvents.filter {
                it.city?.lowercase()?.contains(text.lowercase()) == true
            }
        } else {
            listEvents
        }
        return filteredByCity
    }

    fun searchEventsByPrice(price: String, listEvents: List<Event>): List<Event> {
        val filteredByCity = if (price.isNotEmpty() && price.toDoubleOrNull() != null) {
            listEvents.filter {
                (it.price ?: 0) <= price.toDouble()
            }
        } else {
            listEvents
        }
        return filteredByCity
    }

    @AssistedFactory
    interface Factory :
        AssistedViewModelFactory<ListScreenViewModel, ListScreenState> {
        override fun create(state: ListScreenState): ListScreenViewModel
    }

    companion object :
        MavericksViewModelFactory<ListScreenViewModel, ListScreenState> by hiltMavericksViewModelFactory()
}

data class ListScreenState(
    val listEvents: List<Event> = mutableListOf(),
    val listSearchedEvents: List<Event> = mutableListOf(),
    val city: String = "",
    val price: String = "",
) : MavericksState

