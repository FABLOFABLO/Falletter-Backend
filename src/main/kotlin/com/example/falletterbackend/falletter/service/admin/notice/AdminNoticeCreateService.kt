package com.example.falletterbackend.falletter.service.admin.notice

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.dto.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.entity.notice.Notice
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminNoticeCreateService(
    private val noticeFacade: NoticeFacade,
    private val userFacade: UserFacade
) {
    @AdminOnly
    @CacheEvict(value = ["notices"], allEntries = true)
    @Transactional
    fun execute(request: NoticeCreateRequest) {
        val user = userFacade.getCurrentUser()

        val notice = Notice(
            title = request.title,
            content = request.content,
            author = user
        )

        noticeFacade.save(notice)
    }
}
