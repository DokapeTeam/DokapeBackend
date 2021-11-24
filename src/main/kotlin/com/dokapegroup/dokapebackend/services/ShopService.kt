package com.dokapegroup.dokapebackend.services

import com.dokapegroup.dokapebackend.models.DBShop
import com.dokapegroup.dokapebackend.repositories.ShopRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ShopService @Autowired constructor(
    private val shopRepository: ShopRepository
) {
    fun getShopById(id: Long) = shopRepository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    fun addShop(dbShop: DBShop) = shopRepository.saveAndFlush(dbShop)
}
