package com.example.edemapi.entities.response

import com.example.edemapi.entities.Time
import javax.persistence.*

data class ShowPushResponse (
        val id : Long? = null,
        var title : String? = null,
        var body : String? = null,

        var time : Time? = null,

        var lang : String? = null,

        var geo : String? = null,

        var appPackage : String? = null,
)