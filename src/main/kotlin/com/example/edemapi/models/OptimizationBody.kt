package com.example.edemapi.models

import java.util.*

data class OptimizationBody(
        val appsflyer_id  : String,
        val eventName : String,
        var eventCurrency : String? = "USD",
        var eventTime : String? = Date().toString(),
        var eventValue : EventValue = EventValue(),
)

data class EventValue(
        var af_price : Int = 1,
        var af_currency : String = "USD"
)
