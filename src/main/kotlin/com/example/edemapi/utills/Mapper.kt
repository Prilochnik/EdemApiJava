package com.example.edemapi.utills

import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.InstallRequest

object Mapper {
    fun mapInstallRequestToUserEntity(installRequest: InstallRequest) =
        UserEntity(
             userId = installRequest.userId,
             appPackage = installRequest.appPackage,
             locale  = installRequest.locale,
             campaign  = installRequest.campaign,
             campaignId  = installRequest.campaignId,
             afAdset  = installRequest.afAdset,
             afAdsetId  = installRequest.afAdsetId,
             afAd  = installRequest.afAd,
             pushToken  = installRequest.pushToken,
             afAdId  = installRequest.afAdId,
             lang  = installRequest.lang,
        )
}