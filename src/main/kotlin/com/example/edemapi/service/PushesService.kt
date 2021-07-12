package com.example.edemapi.service

import com.example.edemapi.entities.AppEntity
import com.example.edemapi.entities.PushEntity
import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.pushes.AddPushRequest
import com.example.edemapi.entities.requests.pushes.SingleGeoPushRequest
import com.example.edemapi.entities.requests.pushes.SingleLangPushRequest
import com.example.edemapi.entities.response.ShowPushResponse
import com.example.edemapi.exceptions.customExceptions.NoAppFoundException
import com.example.edemapi.exceptions.customExceptions.NoPushFoundException
import com.example.edemapi.models.CNotification
import com.example.edemapi.models.GeoPush
import com.example.edemapi.models.PushResponse
import com.example.edemapi.repos.AppRepository
import com.example.edemapi.repos.PushRepository
import com.example.edemapi.repos.UserRepository
import com.example.edemapi.utills.Mapper
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

    fun addPush(pushRequest: AddPushRequest){
        pushRepository.save(Mapper.mapAddPushRequestToPushEntity(pushRequest))
    }

    fun changePushById(pushEntity: PushEntity, pushId : Long){
        val push = pushRepository.findById(pushId).orElseThrow { NoPushFoundException("Error in change Push") }
        push.apply {
            appPackage = pushEntity.appPackage
            body = pushEntity.body
            title = pushEntity.title
            time = pushEntity.time
            lang = pushEntity.lang
            geo = pushEntity.geo
        }
        pushRepository.save(push)
    }

    fun showPushesDetails() =
            pushRepository.findAll().toList().sortedBy { it.id }


    fun removePushById(id : Long){
        val push = pushRepository.findById(id).orElseThrow{ NoPushFoundException("Error while found push to remove") }
        push.users!!.forEach {
            it.pushId = null
        }
        pushRepository.delete(push)
    }

    fun showPushes() =
            pushRepository.findAll().toList().map {
                ShowPushResponse(it.id, it.title, it.body, it.time, it.lang, it.geo, it.appPackage)
            }.sortedBy { it.id }


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