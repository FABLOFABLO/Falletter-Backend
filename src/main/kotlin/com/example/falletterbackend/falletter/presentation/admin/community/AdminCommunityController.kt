package com.example.falletterbackend.falletter.presentation.admin.community

import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.admin.community.AdminCommunityService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Admin Community", description = "관리자 커뮤니티 관리 API")
@RestController
@RequestMapping("/admin")
class AdminCommunityController(
    private val adminCommunityService: AdminCommunityService
) {
    @Operation(summary = "게시글 삭제 처리", description = "게시글의 삭제 상태를 변경합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "상태 변경 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음"),
        ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음"),
        ApiResponse(responseCode = "409", description = "이미 삭제된 게시글")
    )
    @PatchMapping(RestApiSpec.ADMIN_COMMUNITY_STATUS)
    @ResponseStatus(HttpStatus.OK)
    fun updateCommunityStatus(
        @Parameter(description = "게시글 ID", example = "1")
        @PathVariable("community-id") id: Long
    ) {
        adminCommunityService.deleteCommunity(id)
    }
}
