package com.dokapegroup.dokapebackend.services

import com.dokapegroup.dokapebackend.repositories.ShortPagingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ShortService @Autowired constructor(
    private val shortRepository: ShortPagingRepository
) {
    fun getShortsPaging(userId: Long, pageable: Pageable) = shortRepository.findAllByUserId(userId, pageable)
}
