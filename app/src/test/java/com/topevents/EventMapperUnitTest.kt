package com.topevents

import com.topevents.data.model.Event
import com.topevents.data.model.Events
import com.topevents.domain.getEventsToDisplay
import com.topevents.util.toStringCurrencyFormat
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class EventMapperUnitTest {

    @Test
    fun eventsMapper() {
        val fullEventList = getMockedEvents()
        val displayEvents: List<Event> = fullEventList.getEventsToDisplay()

        assertEquals(4, fullEventList.events.size)
        assertEquals(3, displayEvents.size)

        displayEvents.forEachIndexed { index, item ->
            println("Item: " + item.name)
            assertEquals("$${index + 1}.00", item.price.toStringCurrencyFormat())
        }
    }

    private fun getMockedEvents(): Events {
        val eventFour = Event(
            "Event Four",
            "4/11/2018",
            8,
            3.0,
            "Venue",
            listOf("play", "test")
        )
        val eventTwo = Event(
            "Event One",
            "1/11/2018",
            4,
            1.0,
            "Venue",
            listOf()
        )
        val eventThre = Event(
            "Event Two",
            "2/11/2018",
            2,
            2.0,
            "Venue",
            listOf("play", "event")
        )
        val eventOne = Event(
            "Event Three",
            "3/11/2018",
            0,
            123.0,
            "Venue",
            listOf()
        )

        return Events(
            listOf(
                eventOne,
                eventTwo,
                eventThre,
                eventFour
            )
        )
    }
}