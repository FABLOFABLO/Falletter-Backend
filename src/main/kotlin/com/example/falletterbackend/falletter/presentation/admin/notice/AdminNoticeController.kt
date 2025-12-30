package com.example.falletterbackend.falletter.presentation.admin.notice

import com.example.falletterbackend.falletter.dto.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.dto.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.dto.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.notice.AdminNoticeCreateService
import com.example.falletterbackend.falletter.service.notice.AdminNoticeDeleteService
import com.example.falletterbackend.falletter.service.notice.AdminNoticeGetAllService
import com.example.falletterbackend.falletter.service.notice.AdminNoticeGetDetailService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Admin Notice", description = "관리자 공지사항 API")
@RestController
@RequestMapping("/admin")
class AdminNoticeController(
    private val AdminnoticeCreateService: AdminNoticeCreateService,
    private val AdminnoticeGetAllService: AdminNoticeGetAllService,
    private val AdminnoticeGetDetailService: AdminNoticeGetDetailService,
    private val AdminnoticeDeleteService: AdminNoticeDeleteService
) {
    @Operation(summary = "공지사항 등록", description = "새로운 공지사항을 등록합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "공지사항 등록 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음")
    )
    @PostMapping(RestApiSpec.NOTICE_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun createNotice(@RequestBody request: NoticeCreateRequest) {
        AdminnoticeCreateService.execute(request)
    }

    @Operation(summary = "공지사항 목록 조회", description = "전체 공지사항 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.NOTICE_LIST)
    @ResponseStatus(HttpStatus.OK)
    fun getAllNotices(): List<NoticeListResponse> { return AdminnoticeGetAllService.execute() }

    @Operation(summary = "공지사항 상세 조회", description = "공지사항 상세 내용을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "404", description = "공지사항을 찾을 수 없음")
    )
    @GetMapping(RestApiSpec.NOTICE_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun getNoticeDetail(
        @Parameter(description = "공지사항 ID", example = "1")
        @PathVariable("notice-id") id: Long
    ): NoticeDetailsResponse {
        return AdminnoticeGetDetailService.execute(id)
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다. (ADMIN 전용)")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "삭제 성공"),
        ApiResponse(responseCode = "403", description = "권한 없음"),
        ApiResponse(responseCode = "404", description = "공지사항을 찾을 수 없음")
    )
    @DeleteMapping(RestApiSpec.NOTICE_DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNotice(
        @Parameter(description = "공지사항 ID", example = "1")
        @PathVariable("notice-id") id: Long
    ) {
        AdminnoticeDeleteService.execute(id)
    }
}