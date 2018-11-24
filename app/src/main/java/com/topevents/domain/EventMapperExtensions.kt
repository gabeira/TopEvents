package com.topevents.domain

import com.topevents.data.model.Event
import com.topevents.data.model.Events

/**
 * Created by gabeira@gmail.com on 23/11/18.
 */
fun Events.getEventsToDisplay(): List<Event>{
    return this.events
        .filter { it.available_seats > 0 }
        .sortedBy { it.date }
}

const val EVENT_PLAY_LABEL = "play"