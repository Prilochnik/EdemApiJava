package com.example.edemapi.controllers

import com.example.edemapi.entities.requests.InstallRequest
import com.example.edemapi.entities.response.InstallResponse
import com.example.edemapi.service.InstallService
import org.springframework.web.bind.annotation.RestController
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@RestController
class UserController(
    val installService: InstallService,
    val objectMapper: ObjectMapper
    ) {


    @PostMapping("/install")
    fun install(@RequestBody jsonRequest : String) : ResponseEntity<InstallResponse>{
        val request = objectMapper.readValue(jsonRequest, InstallRequest::class.java)
        return ResponseEntity(InstallResponse(true, "testUser"), HttpStatus.ACCEPTED)


    }
}