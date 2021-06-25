package com.example.edemapi.repos

import com.example.edemapi.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findAllByAppPackage(appPackage : String) : List<UserEntity>
    fun findAllByUserId(userId : String) : List<UserEntity>
    fun findAllByGeoAndAppPackage(geo : String, appPackage: String) : List<UserEntity>
    fun findAllByLangAndAppPackage(geo : String, appPackage: String) : List<UserEntity>
}