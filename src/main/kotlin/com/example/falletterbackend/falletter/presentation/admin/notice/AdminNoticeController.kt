package com.example.falletterbackend.falletter.presentation.admin.notice

import com.example.falletterbackend.falletter.dto.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.dto.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.dto.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.notice.AdminNoticeCreateService
import com.example.falletterbackend.falletter.service.notice.AdminNoticeDeleteService
import com.example.falletterbackend.falletter.service.notice.AdminNoticeGetAllService
import com.example.falletterbackend.falletter.service.notice.AdminNoticeGetDetailService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminNoticeController(
    private val AdminnoticeCreateService: AdminNoticeCreateService,
    private val AdminnoticeGetAllService: AdminNoticeGetAllService,
    private val AdminnoticeGetDetailService: AdminNoticeGetDetailService,
    private val AdminnoticeDeleteService: AdminNoticeDeleteService
) {
    @PostMapping(RestApiSpec.NOTICE_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun createNotice(@RequestBody request: NoticeCreateRequest) {
        AdminnoticeCreateService.execute(request)
    }

    @GetMapping(RestApiSpec.NOTICE_LIST)
    @ResponseStatus(HttpStatus.OK)
    fun getAllNotices(): List<NoticeListResponse> { return AdminnoticeGetAllService.execute() }

    @GetMapping(RestApiSpec.NOTICE_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun getNoticeDetail(@PathVariable("notice-id") id: Long): NoticeDetailsResponse {
        return AdminnoticeGetDetailService.execute(id)
    }

    @DeleteMapping(RestApiSpec.NOTICE_DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNotice(@PathVariable("notice-id") id: Long) {
        AdminnoticeDeleteService.execute(id)
    }
}