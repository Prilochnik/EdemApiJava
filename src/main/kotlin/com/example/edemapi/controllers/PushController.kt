package com.example.edemapi.controllers

import com.example.edemapi.entities.PushEntity
import com.example.edemapi.entities.requests.pushes.AddPushRequest
import com.example.edemapi.entities.requests.pushes.SingleGeoPushRequest
import com.example.edemapi.entities.requests.pushes.SingleLangPushRequest
import com.example.edemapi.service.PushesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PushController (
        private val pushesService: PushesService,
){

    @PostMapping("/addPush")
    fun addPush(@RequestBody push : AddPushRequest): ResponseEntity<String> {
        pushesService.addPush(push)
        return ResponseEntity("Ok", HttpStatus.ACCEPTED)
    }

    @PostMapping("/removePushById")
    fun removePushById(@RequestBody id : Long): ResponseEntity<String>{
        pushesService.removePushById(id)
        return ResponseEntity("Ok", HttpStatus.ACCEPTED)
    }

    @PostMapping("/showPushes")
    fun showPushes(): ResponseEntity<List<PushEntity>> {
        val pushes = pushesService.showPushes()
        return ResponseEntity(pushes, HttpStatus.OK)
    }

    @PostMapping("/singleGeoPush")
    fun singleGeoPush(@RequestBody singleGeoPushRequest: SingleGeoPushRequest): ResponseEntity<String> {
        println(singleGeoPushRequest)
        pushesService.singleGeoPush(singleGeoPushRequest)
        return ResponseEntity("Я Пропушил, если кому-то что-то не дошло, вл всем виноваты ебаные сяоми", HttpStatus.OK)
    }

    @PostMapping("/singleLangPush")
    fun singleLangPush(@RequestBody singleLangPushRequest: SingleLangPushRequest): ResponseEntity<String> {
        pushesService.singleLangPush(singleLangPushRequest)
        return ResponseEntity("Я Пропушил, если кому-то что-то не дошло, вл всем виноваты ебаные сяоми", HttpStatus.OK)
    }
}