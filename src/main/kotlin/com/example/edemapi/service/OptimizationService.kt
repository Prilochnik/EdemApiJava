package com.example.edemapi.service

import com.example.edemapi.exceptions.customExceptions.DecodeOptimizationStatusException
import com.example.edemapi.exceptions.customExceptions.NoAppFoundException
import com.example.edemapi.models.OptimizationBody
import com.example.edemapi.repos.AppRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class OptimizationService(
        val appRepository: AppRepository
) {

    fun optimization(id : String, status : String, appPackage: String): String? {
         //= OptimizationBody(id, status)
        val body = when(status.toLowerCase()){
            "lead" -> OptimizationBody(id, "af_register")
            "sale" -> OptimizationBody(id, "af_app_purchase")
            else -> throw DecodeOptimizationStatusException("error in event")
        }
        val app = appRepository.findByAppPackage(appPackage).orElseThrow { NoAppFoundException("Error in event") }
        return postToApps(appPackage, app.devKey!!, body)
    }

    fun postToApps(appPackage : String, devKey : String, body : OptimizationBody) =
        WebClient.create("https://api2.appsflyer.com/inappevent/$appPackage")
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .header("authentication", devKey)
                .body(Mono.just(body), OptimizationBody::class.java)
                .retrieve()
                .bodyToMono(String::class.java)
                .block()


}