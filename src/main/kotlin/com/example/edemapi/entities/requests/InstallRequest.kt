package com.example.edemapi.entities.requests

data class InstallRequest (
        var userId : String? = null,
        var appPackage : String? = null,
        var locale : String? = null,
        var campaign : String? = null,
        var campaignId : String? = null,
        var afAdset : String? = null,
        var afAdsetId : String? = null,
        var afAd : String? = null,
        var pushToken : String? = null,
        var afAdId : String? = null,
        var lang : String? = null,
)