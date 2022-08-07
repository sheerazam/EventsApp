package com.example.andleatask

import com.example.andleatask.data.model.Children
import com.example.andleatask.data.model.Event
import kotlinx.serialization.json.Json
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun serialize() {
        val json = Json.decodeFromString(Children.serializer(), exampleJson)
        assertTrue(json.children?.isNotEmpty() == true)
    }

    @Test
    fun getEventsTest() {
        val json = Json.decodeFromString(Children.serializer(), exampleJson)
        val events = getEventsRec(json)
        assertEquals(events.size, 17)
    }

    fun getEventsRec(eventsResponse : Children) : List<Event>{
        val listEvents = mutableListOf<Event>()
        eventsResponse.events?.forEach {event ->
            listEvents.add(event)
        }
        if(eventsResponse.children?.isNotEmpty() == true){
            eventsResponse.children?.forEach { child ->
                listEvents.addAll(getEventsRec(child))
            }
        }
        return listEvents
    }
}