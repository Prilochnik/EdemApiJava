package com.example.edemapi.controllers

import com.example.edemapi.service.OptimizationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
        val optimizationService : OptimizationService
) {

    @GetMapping("/")
    fun home(): ResponseEntity<String> {
        return ResponseEntity("ladno, ya jiv", HttpStatus.OK)
    }

    @GetMapping("/optimaze")
    fun optimization(@RequestParam id : String, @RequestParam status : String, @RequestParam app_package : String) : ResponseEntity<String>{
        val response = optimizationService.optimization(id, status, app_package)
        return ResponseEntity(response, HttpStatus.OK)
    }
}