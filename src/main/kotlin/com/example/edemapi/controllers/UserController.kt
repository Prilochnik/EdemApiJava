package com.example.edemapi.controllers

import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.InstallRequest
import com.example.edemapi.entities.response.InstallResponse
import com.example.edemapi.service.InstallService
import com.example.edemapi.service.UserService
import org.springframework.web.bind.annotation.RestController
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.servlet.http.HttpServletRequest


@RestController
class UserController(
    val installService: InstallService,
    val objectMapper: ObjectMapper,
    val userService: UserService
    ) {


    @PostMapping(value = ["/install"])
           // consumes =  MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
           // produces = {MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE })
    fun install(@RequestBody jsonRequest : String, request : HttpServletRequest) : ResponseEntity<InstallResponse>{
        //println("servlet${request.remoteAddr}" )
        val installRequest = objectMapper.readValue(jsonRequest, InstallRequest::class.java)
       // println(installRequest)
        val link = installService.getLink(installRequest, "176.122.117.188")//request.remoteAddr)
        return if(link != null)
            ResponseEntity(InstallResponse(true, installRequest.user_id!!, link), HttpStatus.ACCEPTED)
        else
            ResponseEntity(InstallResponse(false, "testUser"), HttpStatus.ACCEPTED)
    }

    @GetMapping("/showUsers")
    fun showUser(): ResponseEntity<List<UserEntity>> {
        val users = userService.showUsers()
        return ResponseEntity(users, HttpStatus.ACCEPTED)
    }
}