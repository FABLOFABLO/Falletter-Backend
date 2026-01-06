package com.example.falletterbackend.falletter.dto.image.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "이미지 업로드 응답")
data class ImageUploadResponse(
    @Schema(description = "업로드된 이미지 URL", example = "https://bucket.s3.ap-northeast-2.amazonaws.com/profile/uuid.jpeg")
    val imageUrl: String
)
