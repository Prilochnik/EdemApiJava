package com.example.edemapi.service

import com.example.edemapi.entities.BlackNetEntity
import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.InstallRequest
import com.example.edemapi.exceptions.customExceptions.NoAppFoundException
import com.example.edemapi.repos.AppRepository
import com.example.edemapi.repos.BlackNetRepository
import com.example.edemapi.repos.PushRepository
import com.example.edemapi.repos.UserRepository
import com.example.edemapi.utills.Mapper
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@Service
class InstallService(
    private val appRepository: AppRepository,
    private val userRepository: UserRepository,
    private val geoService: GeoService,
    private val pushRepository: PushRepository,
    private val blackNetRepository: BlackNetRepository,
    private val blackUserService: BlackUserService

) {

    fun getLink(installRequest: InstallRequest, ip : String) : String?{
        val user = Mapper.mapInstallRequestToUserEntity(installRequest)
        val app = appRepository.findByAppPackage(user.appPackage!!).orElseThrow { NoAppFoundException("Error in install") }
        user.date = Date()
        user.ip = ip
        if(checkIp(ip)){
            //Not bot

            val geo = geoService.checkGeo(ip, app.banGeo!!)

            try{
                user.geo = geo.geo?.country?.name_en
                user.locale = geo.geo?.country!!.utc.toString()
            } catch (e : Exception){
                user.geo = null
                user.locale = null
            }
            setPush(user)
            if(user.campaign != null){
                //non organic
                val link = createNonOrganicLink(app.link!!, user)
                user.result = "true | $link "
                userRepository.save(user)
                return link
            }
            else { //organic
                if(app.organic == false){
                    blackUserService.addUser(user)
                    return null
                }
                return if(app.banGeo != null && geo.allow){
                    //бан гео не нулл или это гео в бане
                    blackUserService.addUser(user)
                    null
                }
                else{
                    user.result = "true | ${app.organicLink} "
                    userRepository.save(user)
                    app.organicLink
                }
            }
        }
        else{
            //Bot
            val geo = geoService.checkGeo(ip, app.banGeo!!)
            user.geo = geo.geo?.country?.name_en
            blackUserService.addUser(user)
            return null
        }
    }

    fun setPush(user : UserEntity){
        val pushes = pushRepository.findAll()
        pushes.forEach { push ->
            if(user.pushId == null && push.appPackage == user.appPackage && push.geo == user.geo)
                user.pushId = push
        }
        pushes.forEach { push ->
            if(user.pushId == null && push.appPackage == "all" && push.geo == user.geo)
                user.pushId = push
        }
        pushes.forEach { push ->
            if(user.pushId == null && push.appPackage == "all" && push.lang == user.lang)
                user.pushId = push
        }
        pushes.forEach { push ->
            if(user.pushId == null && push.lang == user.lang)
                user.pushId = push
        }
        pushes.forEach { push ->
            if(user.pushId == null && push.lang == "en")
                user.pushId = push
        }
//        pushRepository.findAll().forEach { push ->
//            if(user.pushId == null){
//                if(push.appPackage == user.appPackage && push.geo == user.geo)
//                    user.pushId = push
//                else {
//                    if (push.appPackage == "all" && push.geo == user.geo)
//                        user.pushId = push
//                    else{
//                        if(push.appPackage == "all" && push.lang == user.lang)
//                            user.pushId = push
//                        else{
//                            if(push.lang == user.lang)
//                                user.pushId = push
//                            else
//                                if(push.lang == "en")
//                                    user.pushId = push
//                        }
//                    }
//                }
//            }
//        }

    }

    fun checkIp(ip : String) : Boolean{
        //todo get black ips and check
        val blacks = blackNetRepository.findAll()
        blacks.forEach {
            if(it.net?.let { it1 -> checkIpHelper(ip, it1) } == false)
                return false
        }
        return true
    }

    fun checkIpHelper(ip : String, ipDb : String): Boolean {
        val mask = ipDb.split("/")[1]
        val ipDbArray = getIpArray(ipDb.split('/')[0])
        val ipArray = getIpArray(ip)
        for(i in 0 until mask.toInt())
            if(ipArray[i] != ipDbArray[i]) return true
        return false
    }

    fun getIpArray(ip : String): List<String> {
        val arrInt = ip.split(".")
        var arrStr = ""
        arrInt.forEach{
            try {
                arrStr += dec2Bin(it.toInt())
            } catch (e : Exception) {
                return@forEach
            }

        }

        return arrStr.split("")
    }

    fun dec2Bin(dec : Int): String {
        var a = dec.toString(2)
        while(a.length < 8)
            a = "0$a"
        return a
    }


    fun createNonOrganicLink(host : String, user : UserEntity) : String {
        val endLink = naming(user.campaign!!)
        return "$host?af_id=${user.userId}&app_name=${user.appPackage}&с=${user.campaign}&af_c_id=${user.campaignId}&af_adset=${user.afAdset}&af_adset_id=${user.afAdsetId}&af_ad=${user.afAd}&af_ad_id=${user.afAdId}&$endLink"
    }

    fun naming(c : String) =
        if(c != "") c.split("|^|").joinToString("&") else c
}