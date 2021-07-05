package com.example.edemapi.utills

import com.example.edemapi.entities.BlackUserEntity
import com.example.edemapi.entities.PushEntity
import com.example.edemapi.entities.UserEntity
import com.example.edemapi.entities.requests.InstallRequest
import com.example.edemapi.entities.requests.pushes.AddPushRequest

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
             lang  = installRequest.language,
            mediaSource = installRequest.mediaSource
        )

    fun mapUserEntityToBlackUserEntity(userEntity: UserEntity) =
            BlackUserEntity(
                    userId = userEntity.userId,
                    appPackage = userEntity.appPackage,
                    locale  = userEntity.locale,
                    campaign  = userEntity.campaign,
                    campaignId  = userEntity.campaignId,
                    afAdset  = userEntity.afAdset,
                    afAdsetId  = userEntity.afAdsetId,
                    afAd  = userEntity.afAd,
                    pushToken  = userEntity.pushToken,
                    afAdId  = userEntity.afAdId,
                    lang  = userEntity.lang,
                    mediaSource = userEntity.mediaSource
            )

    fun mapAddPushRequestToPushEntity(pushRequest: AddPushRequest) =
            PushEntity(
                    title = pushRequest.title,
                    body = pushRequest.body,
                    time = pushRequest.time,
                    lang = pushRequest.lang,
                    geo = pushRequest.geo,
                    appPackage = pushRequest.appPackage
            )
}