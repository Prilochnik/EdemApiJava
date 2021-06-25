package com.example.edemapi.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun home(): ResponseEntity<String> {
        return ResponseEntity("ladno, ya jiv", HttpStatus.OK)
    }
}