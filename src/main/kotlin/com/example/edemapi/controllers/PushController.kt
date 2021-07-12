package com.example.edemapi.controllers

import com.example.edemapi.entities.PushEntity
import com.example.edemapi.entities.requests.pushes.AddPushRequest
import com.example.edemapi.entities.requests.pushes.ChangePushRequest
import com.example.edemapi.entities.requests.pushes.SingleGeoPushRequest
import com.example.edemapi.entities.requests.pushes.SingleLangPushRequest
import com.example.edemapi.entities.response.ShowPushResponse
import com.example.edemapi.service.PushesService
import com.example.edemapi.utills.Mapper
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
    fun removePushById(@RequestBody json : Map<String, String>): ResponseEntity<String>{
        println(json["id"])
        pushesService.removePushById(json["id"]!!.toLong())
        return ResponseEntity("Ok", HttpStatus.ACCEPTED)
    }

    @PostMapping("/showPushes")
    fun showPushes(): ResponseEntity<List<ShowPushResponse>> {
        val pushes = pushesService.showPushes()
        return ResponseEntity(pushes, HttpStatus.OK)
    }

    @PostMapping("/showPushesDetails")
    fun showPushesDetails(): ResponseEntity<List<PushEntity>> {
        val pushes = pushesService.showPushesDetails()
        return ResponseEntity(pushes, HttpStatus.OK)
    }

    @PostMapping("/singleGeoPush")
    fun singleGeoPush(@RequestBody singleGeoPushRequest: SingleGeoPushRequest): ResponseEntity<String> {
        pushesService.singleGeoPush(singleGeoPushRequest)
        return ResponseEntity("Я Пропушил, если кому-то что-то не дошло, вл всем виноваты ебаные сяоми", HttpStatus.OK)
    }

    @PostMapping("/changePushById")
    fun changePushById(@RequestBody push : ChangePushRequest){
        pushesService.changePushById(pushEntity = Mapper.mapChangePushRequestToPushEntity(push), pushId = push.id.toLong())
    }

    @PostMapping("/singleLangPush")
    fun singleLangPush(@RequestBody singleLangPushRequest: SingleLangPushRequest): ResponseEntity<String> {
        pushesService.singleLangPush(singleLangPushRequest)
        return ResponseEntity("Я Пропушил, если кому-то что-то не дошло, вл всем виноваты ебаные сяоми", HttpStatus.OK)
    }
}