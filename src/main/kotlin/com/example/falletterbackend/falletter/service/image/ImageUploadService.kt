package com.example.falletterbackend.falletter.service.image

import com.example.falletterbackend.common.utils.s3.S3Utils
import com.example.falletterbackend.falletter.dto.image.response.ImageUploadResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageUploadService(
    private val s3Utils: S3Utils,
    @Value("\${cloud.aws.s3.profile-path}") private val profilePath: String
) {
    fun execute(file: MultipartFile): ImageUploadResponse {
        val imageUrl = s3Utils.upload(file, profilePath)
        return ImageUploadResponse(imageUrl)
    }
}
