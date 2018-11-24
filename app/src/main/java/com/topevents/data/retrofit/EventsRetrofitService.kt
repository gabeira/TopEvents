package com.topevents.data.retrofit

import com.topevents.data.model.Events
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Created by gabeira@gmail.com on 22/11/18.
 */
interface EventsRetrofitService {

    @GET("events.json")
    fun requestEvents(): Deferred<Events>
}