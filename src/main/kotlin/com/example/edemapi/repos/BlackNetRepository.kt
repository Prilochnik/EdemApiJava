package com.example.edemapi.repos

import com.example.edemapi.entities.BlackNetEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BlackNetRepository : JpaRepository<BlackNetEntity, Long> {
}