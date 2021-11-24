@file:Suppress("NOTHING_TO_INLINE")

package com.dokapegroup.dokapebackend.extensions

import com.dokapegroup.dokapebackend.utils.AuthToken
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

inline fun AuthToken?.removeBearer() =
    this?.token?.removePrefix("Bearer ") ?: throw ResponseStatusException(HttpStatus.FORBIDDEN)
