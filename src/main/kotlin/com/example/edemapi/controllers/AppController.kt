package com.example.edemapi.controllers

import com.example.edemapi.entities.AppEntity
import com.example.edemapi.entities.requests.app.AddAppRequest
import com.example.edemapi.entities.requests.app.ChangeBanGeoRequest
import com.example.edemapi.entities.requests.app.ChangeLinksRequest
import com.example.edemapi.entities.response.InstallResponse
import com.example.edemapi.service.AppService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AppController(
        val appService : AppService
        ) {

    @PostMapping("/addApp")
    fun addApp(@RequestBody addAppRequest : AddAppRequest): ResponseEntity<String> {
        appService.addApp(addAppRequest)
        return ResponseEntity<String>("Success", HttpStatus.ACCEPTED)
    }

    @GetMapping("/showApps")
    fun showApps(): ResponseEntity<List<AppEntity>> {
        val apps = appService.showApps()
        return ResponseEntity(apps, HttpStatus.ACCEPTED)
    }

    @PostMapping("/organicOff")
    fun organicOff(@RequestBody app_package : String) : ResponseEntity<String>{
        appService.organicOff(app_package)
        return ResponseEntity("Success", HttpStatus.ACCEPTED)
    }

    @PostMapping("/organicOn")
    fun organicOn(@RequestBody app_package : String) : ResponseEntity<String>{
        appService.organicOn(app_package)
        return ResponseEntity("Success", HttpStatus.ACCEPTED)
    }

    @PostMapping("/changeAppLinks")
    fun changeLinks(@RequestBody changeLinksRequest: ChangeLinksRequest) : ResponseEntity<String>{
        appService.changeLinks(changeLinksRequest)
        return ResponseEntity("Success", HttpStatus.ACCEPTED)
    }

    @PostMapping("/changeBanGeo")
    fun changeBanGeo(@RequestBody changeBanGeoRequest: ChangeBanGeoRequest) : ResponseEntity<String>{
        appService.changeBanGeo(changeBanGeoRequest)
        return ResponseEntity("Success", HttpStatus.ACCEPTED)
    }

}