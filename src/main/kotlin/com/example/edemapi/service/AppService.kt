package com.example.edemapi.service

import com.example.edemapi.entities.AppEntity
import com.example.edemapi.entities.requests.app.AddAppRequest
import com.example.edemapi.entities.requests.app.ChangeBanGeoRequest
import com.example.edemapi.entities.requests.app.ChangeLinksRequest
import com.example.edemapi.exceptions.customExceptions.NoAppFoundException
import com.example.edemapi.repos.AppRepository
import org.springframework.stereotype.Service

@Service
class AppService(
        val appRepository: AppRepository
) {

    fun addApp(addAppRequest: AddAppRequest){
        lateinit var appEntity: AppEntity
        addAppRequest.apply {
             appEntity = AppEntity(
                    appPackage = app_package,
                    link = link,
                    devKey = dev_key,
                    organic = organic,
                    banGeo = ban_geo,
                    organicLink = organic_link,
                     authKey = auth_key
            )
        }
        appRepository.save(appEntity)
    }

    fun deleteById(id: Long){
        appRepository.deleteById(id)
    }

    fun showApps(): List<AppEntity> {
        val apps = appRepository.findAll()
        return apps.toList()
    }

    fun organicOff(app_package : String){
        val app = appRepository.findByAppPackage(app_package).orElseThrow { NoAppFoundException("Error in change organic") }
        app.organic = false
        appRepository.save(app)
    }

    fun organicOn(app_package : String){
        val app = appRepository.findByAppPackage(app_package).orElseThrow { NoAppFoundException("Error in change organic") }
        app.organic = true
        appRepository.save(app)
    }

    fun changeLinks(changeLinksRequest: ChangeLinksRequest){
        val app = appRepository.findByAppPackage(changeLinksRequest.app_package).orElseThrow { NoAppFoundException("Error in change organic") }
        app.apply {
            link = changeLinksRequest.link
            organicLink = changeLinksRequest.organic_link
        }
        appRepository.save(app)
    }

    fun changeBanGeo(changeBanGeoRequest: ChangeBanGeoRequest){
        val app = appRepository.findByAppPackage(changeBanGeoRequest.app_package).orElseThrow { NoAppFoundException("Error in change organic") }
        app.banGeo = changeBanGeoRequest.ban_geo
        appRepository.save(app)
    }

    fun removeByPackage(appPackage: String){
        val app = appRepository.findByAppPackage(appPackage).orElseThrow { NoAppFoundException("Error while deleting app") }
        appRepository.delete(app)
        //appRepository.deleteByAppPackage(appPackage)
    }
}