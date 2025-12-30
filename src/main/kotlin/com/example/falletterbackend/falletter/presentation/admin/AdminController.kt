package com.example.falletterbackend.falletter.presentation.admin

import com.example.falletterbackend.falletter.dto.letter.response.AdminLetterUnpassedDetailsResponse
import com.example.falletterbackend.falletter.dto.letter.response.AdminLetterUnpassedListResponse
import com.example.falletterbackend.falletter.dto.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.dto.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.dto.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letter.AdminLetterUnpassedDetailsService
import com.example.falletterbackend.falletter.service.letter.AdminLetterUnpassedListService
import com.example.falletterbackend.falletter.service.notice.NoticeCreateService
import com.example.falletterbackend.falletter.service.notice.NoticeDeleteService
import com.example.falletterbackend.falletter.service.notice.NoticeGetAllService
import com.example.falletterbackend.falletter.service.notice.NoticeGetDetailService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminLetterUnpassedListService: AdminLetterUnpassedListService,
    private val adminLetterUnpassedDetailsService: AdminLetterUnpassedDetailsService,
    private val noticeCreateService: NoticeCreateService,
    private val noticeGetAllService: NoticeGetAllService,
    private val noticeGetDetailService: NoticeGetDetailService,
    private val noticeDeleteService: NoticeDeleteService
) {
    @GetMapping(RestApiSpec.LETTER_BOX_UNPASSED)
    @ResponseStatus(HttpStatus.OK)
    fun adminUnpassedAll(): List<AdminLetterUnpassedListResponse> {
        return adminLetterUnpassedListService.execute()
    }

    @GetMapping(RestApiSpec.LETTER_BOX_UNPASSED_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun adminUnpassedDetail(@PathVariable("letter-id") id: Long): AdminLetterUnpassedDetailsResponse {
        return adminLetterUnpassedDetailsService.execute(id)
    }

    @PostMapping(RestApiSpec.NOTICE_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun createNotice(@RequestBody request: NoticeCreateRequest) {
        noticeCreateService.execute(request)
    }

    @GetMapping(RestApiSpec.NOTICE_LIST)
    @ResponseStatus(HttpStatus.OK)
    fun getAllNotices(): List<NoticeListResponse> {
        return noticeGetAllService.execute()
    }

    @GetMapping(RestApiSpec.NOTICE_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun getNoticeDetail(@PathVariable("notice-id") noticeId: Long): NoticeDetailsResponse {
        return noticeGetDetailService.execute(noticeId)
    }

    @DeleteMapping(RestApiSpec.NOTICE_DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNotice(@PathVariable("notice-id") noticeId: Long) {
        noticeDeleteService.execute(noticeId)
    }
}