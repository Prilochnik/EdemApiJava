package com.example.edemapi.entities.requests.pushes


data class SingleLangPushRequest (
        val app_package : String,
        val body : String,
        val title : String,
        val lang : String
        )