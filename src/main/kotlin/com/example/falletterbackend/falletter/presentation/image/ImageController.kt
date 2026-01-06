package com.example.falletterbackend.falletter.presentation.image

import com.example.falletterbackend.falletter.dto.image.response.ImageUploadResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.image.ImageUploadService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Tag(name = "image", description = "이미지 API")
@RestController
@RequestMapping("/image")
class ImageController(
    private val imageUploadService: ImageUploadService
) {
    @Operation(summary = "이미지 업로드", description = "프로필 이미지를 업로드합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "업로드 성공")
    )
    @PostMapping(RestApiSpec.IMAGE_UPLOAD, consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun upload(@RequestPart("file") file: MultipartFile): ImageUploadResponse {
        return imageUploadService.execute(file)
    }
}
