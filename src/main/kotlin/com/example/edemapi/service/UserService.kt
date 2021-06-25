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

    fun showUsersByPackage(appPackage : String) : List<UserEntity>{
        return userRepository.findAllByAppPackage(appPackage)
    }

    fun pushTokenUpdate(userId : String, pushToken : String){
        val user = userRepository.findAllByUserId(userId).last()
        user.pushToken = pushToken
        userRepository.save(user)

    }




}