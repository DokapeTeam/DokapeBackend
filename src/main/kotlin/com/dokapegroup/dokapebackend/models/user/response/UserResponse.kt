package com.dokapegroup.dokapebackend.models.user.response

import com.dokapegroup.dokapebackend.models.DBPet
import com.dokapegroup.dokapebackend.models.DBShort
import com.dokapegroup.dokapebackend.models.Gender
import java.time.LocalDate
import java.time.LocalDateTime

data class UserResponse(
    var id: Long? = null,
    var username: String,
    var firstName: String,
    var lastName: String,
    var avatar: String? = null,
    var coverPhoto: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var gender: Gender = Gender.Other,
    var dateOfBirth: LocalDate,
    var email: String,
    var phone: String? = null,
    var numberOfPosts: Int,
    var numberOfShorts: Int,
    var numberOfPetsAdopted: Int,
    var trustedOwner: Boolean,
    var shop: Long?,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var role: String,
    var pets: MutableList<DBPet> = mutableListOf(),
    var shorts: MutableList<DBShort> = mutableListOf()
)
