package com.example.edemapi.controllers

import com.example.edemapi.entities.BlackUserEntity
import com.example.edemapi.service.BlackUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BlackUserController(
        private val blackUserService: BlackUserService
) {
    @GetMapping("/showBlackUsers")
    fun getUsers(): ResponseEntity<List<BlackUserEntity>> {
        val users = blackUserService.showAll()
        return ResponseEntity(users, HttpStatus.OK)
    }
}