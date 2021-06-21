package com.example.edemapi.service

import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.InstallRequest
import com.example.edemapi.repos.AppRepository
import com.example.edemapi.repos.UserRepository
import com.example.edemapi.utills.Mapper
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class InstallService(
    val appRepository: AppRepository,
    val userRepository: UserRepository,
    val geoService: GeoService

) {

    fun getLink(installRequest: InstallRequest, ip : String) : String?{
        val user = Mapper.mapInstallRequestToUserEntity(installRequest)
        user.ip = ip
        if(checkIp()){
            //Not bot
            val app = appRepository.findByAppPackage(user.appPackage!!).orElseThrow { Exception("No package found") }
            val geo = geoService.checkGeo(ip, app.banGeo!!)
            user.geo = geo.geo?.country?.name_en
            if(user.campaign != null){
                //non organic
                val link = createNonOrganicLink(app.link!!, user)
                user.result = "true | $link "
                userRepository.save(user)
                return link
            }
            else { //organic
                if(app.organic == false) return null
                return if(app.banGeo != null && !geo.allow){
                    //бан гео не нулл или это гео в бане
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
            return null
        }
    }




    fun checkIp() : Boolean{
        //todo get black ips and check

        return true
    }


    fun createNonOrganicLink(host : String, user : UserEntity) : String {
        val endLink = naming(user.campaign!!)
        return "$host?af_id=${user.userId}&app_name=${user.appPackage}&с=${user.campaign}&af_c_id=${user.campaignId}&af_adset=${user.afAdset}&af_adset_id=${user.afAdsetId}&af_ad=${user.afAd}&af_ad_id=${user.afAdId}&$endLink"
    }

    fun naming(c : String) =
        if(c != "") c.split("|^|").joinToString("&") else c
}