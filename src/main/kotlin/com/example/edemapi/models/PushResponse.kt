package com.example.edemapi.models

data class PushResponse(
    var canonical_ids: Int? = null,
    var failure: Int? = null,
    var multicast_id: Long? = null,
    var results: List<Result>? = null,
    var success: Int? = null
)

data class Result(
        var error: String? = null
)