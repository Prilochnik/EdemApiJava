package com.example.edemapi.utills

import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.InstallRequest

object Mapper {
    fun mapInstallRequestToUserEntity(installRequest: InstallRequest) =
        UserEntity(
             userId = installRequest.user_id,
             appPackage = installRequest.app_package,
             locale  = installRequest.locale,
             campaign  = installRequest.campaign,
             campaignId  = installRequest.campaign_id,
             afAdset  = installRequest.af_adset,
             afAdsetId  = installRequest.af_adset_id,
             afAd  = installRequest.af_ad,
             pushToken  = installRequest.push_token,
             afAdId  = installRequest.adgroup_id,
             lang  = installRequest.lang,
        )

}