package com.example.edemapi.models

data class GeoPush (
        val to : String,
        val notification : CNotification
        )

data class CNotification(
        val body : String,
        val title : String
)