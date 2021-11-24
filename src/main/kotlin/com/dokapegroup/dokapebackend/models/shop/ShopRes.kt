package com.dokapegroup.dokapebackend.models.shop

import java.time.LocalDateTime

data class ShopRes(
    var id: Long? = null,
    var name: String,
    var description: String,
    var totalProducts: Int = 0,
    var numberOfFollowers: Int = 0,
    var user: Long? = null,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
