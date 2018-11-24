package com.topevents.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.topevents.data.model.Events
import com.topevents.data.retrofit.EventsNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by gabeira@gmail.com on 21/11/18.
 * Implementation of View Model for Events LiveData
 * https://developer.android.com/topic/libraries/architecture/viewmodel.html
 */
class EventViewModel : ViewModel() {

    private val observableEventsLiveData: MutableLiveData<Events> = MutableLiveData()

    init {
        loadEvents()
    }

    fun getLiveDataObserver(): LiveData<Events> {
        return observableEventsLiveData
    }

    fun loadEvents() {
        val service = EventsNetwork.makeEventsRetrofitService()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val request = service.requestEvents()
                val eventsDataResponse = request.await()
                if (request.isCompleted) {
                    observableEventsLiveData.value = eventsDataResponse
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}