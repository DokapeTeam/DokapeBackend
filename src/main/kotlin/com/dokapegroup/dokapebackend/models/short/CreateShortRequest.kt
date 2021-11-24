package com.dokapegroup.dokapebackend.models.short

import org.springframework.web.multipart.MultipartFile

data class CreateShortRequest(
    val video: MultipartFile,
    val title: String,
    val description: String
)
