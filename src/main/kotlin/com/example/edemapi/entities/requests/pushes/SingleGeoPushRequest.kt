package com.example.edemapi.entities.requests.pushes


data class SingleGeoPushRequest (
        val app_package : String,
        val body : String,
        val title : String,
        val geo : String
        )