package com.example.falletterbackend.falletter.presentation.notice

import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.service.notice.NoticeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "notice", description = "공지사항 API")
@RestController
@RequestMapping("/notice")
class NoticeController(
    private val noticeService: NoticeService
) {
    @Operation(summary = "공지사항 목록 조회", description = "전체 공지사항 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllNotices(): List<NoticeListResponse> {
        return noticeService.getAllNotices()
    }

    @Operation(summary = "공지사항 상세 조회", description = "공지사항 상세 내용을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "404", description = "공지사항을 찾을 수 없음")
    )
    @GetMapping("/{notice-id}")
    @ResponseStatus(HttpStatus.OK)
    fun getNoticeDetail(
        @Parameter(description = "공지사항 ID", example = "1")
        @PathVariable("notice-id") id: Long
    ): NoticeDetailsResponse {
        return noticeService.getNoticeDetail(id)
    }
}
