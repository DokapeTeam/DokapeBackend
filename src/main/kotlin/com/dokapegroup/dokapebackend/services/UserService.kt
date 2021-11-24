package com.dokapegroup.dokapebackend.services

import com.dokapegroup.dokapebackend.controllers.mapper.DBUserToUserResMapper
import com.dokapegroup.dokapebackend.models.DBUser
import com.dokapegroup.dokapebackend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val dbUserToUserResMapper: DBUserToUserResMapper
) {
    fun getUsers(): List<DBUser> = userRepository.findAll()
    fun getUserById(id: Long): DBUser? = userRepository.findByIdOrNull(id)

    fun registerUser(user: DBUser) = userRepository.save(user)
    fun isUserRegistered(username: String) = userRepository.existsByUsername(username)
    fun updateUser(user: DBUser) = userRepository.save(
        user.apply {
            updatedAt = LocalDateTime.now()
        }
    ).let { dbUserToUserResMapper(it) }
    fun getUserResponse(id: Long) = userRepository.findByIdOrNull(id)?.let { dbUserToUserResMapper(it) }
}
