package com.example.falletterbackend.falletter.service.admin.notice

import com.example.falletterbackend.falletter.exception.auth.AccessDeniedException
import com.example.falletterbackend.falletter.dto.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.entity.notice.Notice
import com.example.falletterbackend.falletter.entity.user.enums.Role
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminNoticeCreateService(
    private val noticeFacade: NoticeFacade,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: NoticeCreateRequest) {
        val user = userFacade.getCurrentUser()

        if (user.role != Role.ADMIN) {
            throw AccessDeniedException
        }

        val notice = Notice(
            title = request.title,
            content = request.content,
            author = user
        )

        noticeFacade.save(notice)
    }
}
