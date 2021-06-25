package com.example.edemapi.controllers

import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.InstallRequest
import com.example.edemapi.entities.response.InstallResponse
import com.example.edemapi.service.InstallService
import com.example.edemapi.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
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
        //println("IP : ${request.remoteAddr}")
        var ipAddress = request.getHeader("X-FORWARDED-FOR")
        if(ipAddress == null)
            ipAddress = "127.0.0.1"
        val link = installService.getLink(installRequest, ipAddress)//request.remoteAddr)
        return if(link != null)
            ResponseEntity(InstallResponse(true, installRequest.user_id!!, link), HttpStatus.OK)
        else
            ResponseEntity(InstallResponse(false, installRequest.user_id!!), HttpStatus.OK)
    }

    @GetMapping("/showUsers")
    fun showUser(): ResponseEntity<List<UserEntity>> {
        val users = userService.showUsers()
        return ResponseEntity(users, HttpStatus.OK)
    }

    @PostMapping("/showUserByPackage")
    fun showUserByPackage(@RequestBody app_package : Map<String, String>): ResponseEntity<List<UserEntity>> {
        val users = userService.showUsersByPackage(app_package["app_package"].toString())
        return ResponseEntity(users, HttpStatus.OK)
    }

    @PostMapping("/pushTokenUpdate")
    fun pushTokenUpdate(@RequestBody json : Map<String, String>): ResponseEntity<String> {
        userService.pushTokenUpdate(json["user_id"]!!, json["push_token"]!!)
        val res = mapOf(Pair("result", "success"))
        return ResponseEntity(objectMapper.writeValueAsString(res), HttpStatus.OK)
    }

}