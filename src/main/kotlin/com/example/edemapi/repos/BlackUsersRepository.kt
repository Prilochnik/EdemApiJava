package com.example.edemapi.repos

import com.example.edemapi.entities.BlackUserEntity
import com.example.edemapi.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BlackUsersRepository : JpaRepository<BlackUserEntity, Long> {
    fun findAllByAppPackage(appPackage : String) : List<BlackUserEntity>
}