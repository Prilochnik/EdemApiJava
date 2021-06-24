package com.example.edemapi.entities.requests

data class InstallRequest (
        var user_id : String? = null,
        var app_package : String? = null,
        var locale : String? = null,
        var campaign : String? = null,
        var campaign_id : String? = null,
        var af_adset : String? = null,
        var af_adset_id : String? = null,
        var af_ad : String? = null,
        var push_token : String? = null,
        var adgroup_id : String? = null,
        var lang : String? = null,
        var mediaSource : String? = null
)