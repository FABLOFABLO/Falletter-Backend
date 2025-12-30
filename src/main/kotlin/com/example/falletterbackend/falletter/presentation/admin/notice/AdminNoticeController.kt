package com.example.falletterbackend.falletter.presentation.admin.notice

import com.example.falletterbackend.falletter.dto.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.dto.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.dto.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.notice.NoticeCreateService
import com.example.falletterbackend.falletter.service.notice.NoticeDeleteService
import com.example.falletterbackend.falletter.service.notice.NoticeGetAllService
import com.example.falletterbackend.falletter.service.notice.NoticeGetDetailService
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
    private val noticeCreateService: NoticeCreateService,
    private val noticeGetAllService: NoticeGetAllService,
    private val noticeGetDetailService: NoticeGetDetailService,
    private val noticeDeleteService: NoticeDeleteService
) {
    @PostMapping(RestApiSpec.NOTICE_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun createNotice(@RequestBody request: NoticeCreateRequest) {
        noticeCreateService.execute(request)
    }

    @GetMapping(RestApiSpec.NOTICE_LIST)
    @ResponseStatus(HttpStatus.OK)
    fun getAllNotices(): List<NoticeListResponse> { return noticeGetAllService.execute() }

    @GetMapping(RestApiSpec.NOTICE_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun getNoticeDetail(@PathVariable("notice-id") id: Long): NoticeDetailsResponse {
        return noticeGetDetailService.execute(id)
    }

    @DeleteMapping(RestApiSpec.NOTICE_DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNotice(@PathVariable("notice-id") id: Long) {
        noticeDeleteService.execute(id)
    }
}