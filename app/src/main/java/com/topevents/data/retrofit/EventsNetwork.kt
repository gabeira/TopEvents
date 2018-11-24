package com.topevents.data.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gabeira@gmail.com on 22/11/18.
 */
class EventsNetwork {
    companion object {

        private const val URL_RETROFIT = "https://s3-ap-southeast-2.amazonaws.com/bridj-coding-challenge/"
        private var gsonConverterFactory: Converter.Factory = GsonConverterFactory.create()

        fun makeEventsRetrofitService(): EventsRetrofitService {
            return Retrofit.Builder()
                .baseUrl(URL_RETROFIT)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(EventsRetrofitService::class.java)
        }
    }
}