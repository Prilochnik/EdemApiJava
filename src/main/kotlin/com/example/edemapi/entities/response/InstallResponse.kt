package com.example.edemapi.entities.response

data class InstallResponse(
        val allow : Boolean,
        val userId : String,
        var goto : String? = null,
)
