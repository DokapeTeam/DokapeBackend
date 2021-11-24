package com.dokapegroup.dokapebackend.repositories

import com.dokapegroup.dokapebackend.models.DBShort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface ShortPagingRepository : PagingAndSortingRepository<DBShort, Long> {
    fun findAllByUserId(user_id: Long, pageable: Pageable): Page<DBShort>
}
