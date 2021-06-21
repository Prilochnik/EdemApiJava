package com.example.edemapi.repos

import com.example.edemapi.entities.AppEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AppRepository : JpaRepository<AppEntity, Long> {

    fun findByAppPackage(appPackage: String) : Optional<AppEntity>
}