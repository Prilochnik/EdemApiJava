package com.example.edemapi.entities.requests.app

data class ChangeLinksRequest(
        val app_package : String,
        val link : String,
        val organic_link : String
)
