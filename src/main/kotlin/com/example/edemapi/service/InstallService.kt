package com.example.edemapi.service

import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.InstallRequest
import com.example.edemapi.repos.AppRepository
import com.example.edemapi.repos.UserRepository
import com.example.edemapi.utills.Mapper
import org.springframework.stereotype.Service

@Service
class InstallService(
    val appRepository: AppRepository,
    val userRepository: UserRepository

) {

    fun getLink(installRequest: InstallRequest) : String?{
        val user = Mapper.mapInstallRequestToUserEntity(installRequest)
        if(checkIp()){
            //Not bot
            val app = appRepository.findByAppPackage(user.appPackage!!).orElseThrow { Exception("No package found") }
            if(user.campaign != null){
                //non organic
                userRepository.save(user)
                return createNonOrganicLink(app.link!!, user)
            }
            else{
                //organic
                //todo check geo
                
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