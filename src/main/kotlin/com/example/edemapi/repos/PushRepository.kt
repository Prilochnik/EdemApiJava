package com.example.edemapi.repos

import com.example.edemapi.entities.PushEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PushRepository : JpaRepository<PushEntity, Long> {
}