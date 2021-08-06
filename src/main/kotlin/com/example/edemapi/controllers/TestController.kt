package com.example.edemapi.controllers

import com.example.edemapi.entities.response.InstallResponse
import com.example.edemapi.repos.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val userRepository : UserRepository
) {

    @GetMapping("/testt")
    fun test(): ResponseEntity<Int> {
        val users = userRepository.findAllByAppPackage("").filter { userEntity ->
            userEntity.campaign?.startsWith("") == true
        }
        return ResponseEntity(users.size, HttpStatus.OK)
    }
}