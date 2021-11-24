package com.dokapegroup.dokapebackend.controllers.mapper

import com.dokapegroup.dokapebackend.models.DBUser
import com.dokapegroup.dokapebackend.models.user.response.UserResponse
import org.springframework.stereotype.Component

@Component
class DBUserToUserResMapper : Mapper<DBUser, UserResponse> {
    override fun invoke(input: DBUser): UserResponse {
        return UserResponse(
            id = input.id,
            username = input.username,
            firstName = input.firstName,
            lastName = input.lastName,
            avatar = input.avatar,
            coverPhoto = input.coverPhoto,
            latitude = input.latitude,
            longitude = input.longitude,
            gender = input.gender,
            dateOfBirth = input.dateOfBirth,
            email = input.email,
            phone = input.phone,
            numberOfPosts = input.numberOfPosts,
            numberOfShorts = input.numberOfShorts,
            numberOfPetsAdopted = input.numberOfPetsAdopted,
            trustedOwner = input.trustedOwner,
            shop = input.shop?.id,
            createdAt = input.createdAt,
            updatedAt = input.updatedAt,
            role = input.role,
            pets = input.pets,
            shorts = input.shorts
        )
    }
}
