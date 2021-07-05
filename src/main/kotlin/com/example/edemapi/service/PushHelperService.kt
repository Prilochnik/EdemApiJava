package com.example.edemapi.service

import com.example.edemapi.models.CNotification
import com.example.edemapi.models.GeoPush
import com.example.edemapi.repos.AppRepository
import com.example.edemapi.repos.UserRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Service
class PushHelperService(
        val appRepository: AppRepository,
        val userRepository: UserRepository,
        val pushesService: PushesService
) {

    @Scheduled(cron = "0 * * * * *")
    fun schedule(){
        println("Schedule")
        appRepository.findAll().forEach { app ->
            userRepository.findAllByAppPackage(app.appPackage!!).forEach { user ->
                if(user.pushId!!.time!!.hour == Date().hours.toString()) {
                    val push = GeoPush(
                            to = user.pushToken!!,
                            notification = CNotification(body = spintaxParse(user.pushId?.body!!), title = spintaxParse(user.pushId?.title!!))
                    )
                    pushesService.pushRequest(authKey = app.authKey!!, bodyGeo = push)
                }
            }
        }
    }

    fun spintaxParse(msg : String): String {
        val msgClean = msg.split('{')[1].split('}')[0]
        val items = msgClean.split('|')
        return items.random()
    }
}

