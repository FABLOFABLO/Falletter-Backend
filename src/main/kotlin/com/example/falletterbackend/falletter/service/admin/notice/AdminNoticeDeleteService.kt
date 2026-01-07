package com.example.falletterbackend.falletter.service.admin.notice

import com.example.falletterbackend.falletter.exception.auth.AccessDeniedException
import com.example.falletterbackend.falletter.entity.user.enums.Role
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminNoticeDeleteService(
    private val noticeFacade: NoticeFacade,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(id: Long) {
        val user = userFacade.getCurrentUser()

        if (user.role != Role.ADMIN) {
            throw AccessDeniedException
        }

        val notice = noticeFacade.getNoticeById(id)

        noticeFacade.delete(notice)
    }
}
