package com.example.edemapi.service

import com.example.edemapi.entities.UserEntity
import com.example.edemapi.repos.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
        val userRepository: UserRepository
) {

    fun showUsers(): List<UserEntity> {
        return userRepository.findAll().toList()
    }


}