package com.example.edemapi.exceptions

import org.springframework.http.HttpStatus

data class ApiError(
        val status : HttpStatus,
        val msg : String,
        val error : String
)