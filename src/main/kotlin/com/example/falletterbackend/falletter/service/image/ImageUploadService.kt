package com.example.falletterbackend.falletter.service.image

import com.example.falletterbackend.common.utils.s3.S3Utils
import com.example.falletterbackend.falletter.dto.image.response.ImageUploadResponse
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageUploadService(
    private val s3Utils: S3Utils
) {
    fun execute(file: MultipartFile): ImageUploadResponse {
        val imageUrl = s3Utils.upload(file, "falletter/profile")
        return ImageUploadResponse(imageUrl)
    }
}
