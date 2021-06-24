package com.example.edemapi.entities.requests.app

data class AddAppRequest(
        val app_package : String? = null,
        val link : String? = null,
        val dev_key : String? = null,
        val organic : Boolean? = null,
        val ban_geo : String? = null,
        val organic_link : String? = null,
        val auth_key : String? = null
)