package com.example.edemapi.entities.requests.pushes

import com.example.edemapi.entities.Time

data class ChangePushRequest (
        val id : String,
        var title : String? = null,
        var body : String? = null,

        var time : Time? = null,

        var lang : String? = null,

        var geo : String? = null,

        var appPackage : String? = null,
        )