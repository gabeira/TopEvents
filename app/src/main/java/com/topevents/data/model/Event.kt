package com.topevents.data.model

/**
 * Created by gabeira@gmail.com on 21/11/18.
 */
data class Event(
    val name: String,
    val date: String,
    val available_seats: Int,
    val price: Double,
    val venue: String,
    val labels: List<String>
)