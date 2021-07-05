package com.example.edemapi.service

import com.example.edemapi.entities.AppEntity
import com.example.edemapi.entities.PushEntity
import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.pushes.SingleGeoPushRequest
import com.example.edemapi.entities.requests.pushes.SingleLangPushRequest
import com.example.edemapi.exceptions.customExceptions.NoAppFoundException
import com.example.edemapi.models.CNotification
import com.example.edemapi.models.GeoPush
import com.example.edemapi.models.PushResponse
import com.example.edemapi.repos.AppRepository
import com.example.edemapi.repos.PushRepository
import com.example.edemapi.repos.UserRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class PushesService (
        private val userRepository: UserRepository,
        private val appRepository: AppRepository,
        private val pushRepository: PushRepository
){

    fun singleGeoPush(pushRequest: SingleGeoPushRequest){
        val app = appRepository.findByAppPackage(pushRequest.app_package).orElseThrow{ NoAppFoundException("Error in install") }
        val users = userRepository.findAllByGeoAndAppPackage(pushRequest.geo, app.appPackage!!)
        singlePush(app, users, CNotification(pushRequest.body, pushRequest.title))
    }

    fun singleLangPush(pushRequest: SingleLangPushRequest){
        val app = appRepository.findByAppPackage(pushRequest.app_package).orElseThrow{ NoAppFoundException("Error in install") }
        val users = userRepository.findAllByLangAndAppPackage(pushRequest.lang, app.appPackage!!)
        singlePush(app, users, CNotification(pushRequest.body, pushRequest.title))
    }

    fun singlePush(app : AppEntity, users : List<UserEntity>, notif: CNotification){
        app.authKey?.let { authKey ->
            users.forEach { user ->
                if(user.pushToken != null) {
                    val a = pushRequest(authKey = authKey, GeoPush(user.pushToken!!, notif))//CNotification(title = pushRequest.title, body = pushRequest.body)))
                    println(a)
                }
            }
            //pushRequest(authKey = it, GeoPush(""))
        }
    }

    fun addPush(pushEntity: PushEntity){
        pushRepository.save(pushEntity)
    }

    fun showPushes() =
            pushRepository.findAll().toList()


    fun removePushById(id : Long){
        pushRepository.deleteById(id)
    }


    fun pushRequest(authKey : String, bodyGeo : GeoPush) =
        WebClient.create("https://fcm.googleapis.com/fcm/send")
                .post()
                .header("Authorization", "key=$authKey")
                .contentType(MediaType.APPLICATION_JSON)
               // .header("content-type", "application/json")
                .body(Mono.just(bodyGeo), GeoPush::class.java)
                .retrieve()
                .bodyToMono(PushResponse::class.java)
                .block()


}