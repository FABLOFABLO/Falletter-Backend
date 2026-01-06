package com.example.falletterbackend.falletter.service.image

import com.example.falletterbackend.common.service.S3Service
import com.example.falletterbackend.falletter.dto.image.response.ImageUploadResponse
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageUploadService(
    private val s3Service: S3Service
) {
    fun execute(file: MultipartFile): ImageUploadResponse {
        val imageUrl = s3Service.upload(file, "falletter/profile")
        return ImageUploadResponse(imageUrl)
    }
}
