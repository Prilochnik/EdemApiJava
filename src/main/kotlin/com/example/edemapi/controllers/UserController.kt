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
import javax.servlet.http.HttpServletRequest


@RestController
class UserController(
    val installService: InstallService,
    val objectMapper: ObjectMapper
    ) {


    @PostMapping("/install")
    fun install(@RequestBody jsonRequest : String, request : HttpServletRequest) : ResponseEntity<InstallResponse>{
        val installRequest = objectMapper.readValue(jsonRequest, InstallRequest::class.java)
        val link = installService.getLink(installRequest, request.remoteAddr)
        return if(link != null)
            ResponseEntity(InstallResponse(true, installRequest.userId!!, link), HttpStatus.ACCEPTED)
        else
            ResponseEntity(InstallResponse(true, "testUser"), HttpStatus.ACCEPTED)


    }
}