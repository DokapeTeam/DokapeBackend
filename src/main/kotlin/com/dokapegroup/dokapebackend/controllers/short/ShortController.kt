package com.dokapegroup.dokapebackend.controllers.short

import com.dokapegroup.dokapebackend.extensions.copyToUploadAndGetPath
import com.dokapegroup.dokapebackend.extensions.removeBearer
import com.dokapegroup.dokapebackend.jwt.JwtTokenProvider
import com.dokapegroup.dokapebackend.models.DBShort
import com.dokapegroup.dokapebackend.models.short.CreateShortRequest
import com.dokapegroup.dokapebackend.services.ShortService
import com.dokapegroup.dokapebackend.utils.AuthToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import kotlin.io.path.pathString

@RestController
@RequestMapping("/api/v1/shorts")
class ShortController @Autowired constructor(
    private val shortService: ShortService,
    private val tokenProvider: JwtTokenProvider,
) {
    @GetMapping
    fun getUserShortVideos(
        @RequestHeader("Authorization") token: AuthToken,
        @RequestParam("pageSize") pageSize: Int?
    ): Page<DBShort> {
        val userId = tokenProvider.getUserIdFromJWT(token.removeBearer())
        return shortService.getShortsPaging(userId, Pageable.ofSize(pageSize ?: 20))
    }

    @PostMapping
    fun createNewShortVideo(
        @RequestHeader("Authorization") token: AuthToken,
        @ModelAttribute @Valid @RequestBody createShortRequest: CreateShortRequest
    ): DBShort {
        val userId = tokenProvider.getUserIdFromJWT(token.removeBearer())
        val videoPath =
            createShortRequest.video.copyToUploadAndGetPath("uploads/videos/$userId") ?: throw ResponseStatusException(
                HttpStatus.BAD_REQUEST
            )
        return DBShort(
            title = createShortRequest.title,
            description = createShortRequest.description,
            video = videoPath.pathString,
        )
    }
}
