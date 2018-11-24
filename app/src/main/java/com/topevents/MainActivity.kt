package com.topevents

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.topevents.data.model.Event
import com.topevents.data.model.Events
import com.topevents.domain.getEventsToDisplay
import com.topevents.presentation.EventRecyclerViewAdapter
import com.topevents.presentation.EventViewModel
import com.topevents.util.toStringCurrencyFormat
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by gabeira@gmail.com on 21/11/18.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var eventViewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
        eventViewModel.getLiveDataObserver()
            .observe(this, Observer<Events> { data ->
                swipeRefreshLayout.isRefreshing = false
                data?.let { events ->
                    val eventsToDisplay = events.getEventsToDisplay()
                    eventList.adapter = EventRecyclerViewAdapter(eventsToDisplay, onListEventInteractionListener())
                    printEventsToTerminal(eventsToDisplay)
                }
            })

        swipeRefreshLayout.setOnRefreshListener {
            eventViewModel.loadEvents()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (eventViewModel.getLiveDataObserver().hasObservers()) {
            eventViewModel.getLiveDataObserver().removeObservers(this)
        }
    }

    interface OnListEventInteractionListener {
        fun onEventClick(event: Event)
    }

    private fun onListEventInteractionListener(): OnListEventInteractionListener {
        return object : OnListEventInteractionListener {
            override fun onEventClick(event: Event) {
                AlertDialog.Builder(this@MainActivity)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("Event ${event.name}")
                    .setMessage(getEventMessageDescription(event))
                    .setPositiveButton(android.R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun printEventsToTerminal(eventsToDisplay: List<Event>) {
        eventsToDisplay.forEach {
            println("LogEvent ${it.name}, cost ${it.price.toStringCurrencyFormat()} at the ${it.venue}.")
        }

        eventsToDisplay.forEach {
            if (it.labels.contains("play")) {
                println("LogEvent Playing Event ${it.name}, cost ${it.price.toStringCurrencyFormat()} at the ${it.venue}.")
            }
        }
    }

    private fun getEventMessageDescription(event: Event) =
        "Still have ${resources.getQuantityString(
            R.plurals.seats_available,
            event.available_seats, event.available_seats
        )}," +
                "\nLocation ${event.venue}\n" +
                "It cost ${event.price.toStringCurrencyFormat()}."
}