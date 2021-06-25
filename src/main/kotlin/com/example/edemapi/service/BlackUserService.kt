package com.example.edemapi.service

import com.example.edemapi.entities.BlackUserEntity
import com.example.edemapi.entities.UserEntity
import com.example.edemapi.repos.BlackNetRepository
import com.example.edemapi.repos.BlackUsersRepository
import com.example.edemapi.utills.Mapper
import org.springframework.stereotype.Service

@Service
class BlackUserService(
        private val blackNetRepository: BlackUsersRepository
) {
    fun showAll() =
            blackNetRepository.findAll().toList()

    fun addUser(userEntity: UserEntity){
        val blackUser = Mapper.mapUserEntityToBlackUserEntity(userEntity)
        blackNetRepository.save(blackUser)
    }
}