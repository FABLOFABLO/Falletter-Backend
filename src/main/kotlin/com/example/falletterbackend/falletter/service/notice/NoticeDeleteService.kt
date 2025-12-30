package com.example.falletterbackend.falletter.service.notice

import com.example.falletterbackend.common.exception.AccessDeniedException
import com.example.falletterbackend.falletter.entity.notice.repository.NoticeRepository
import com.example.falletterbackend.falletter.entity.user.enums.Role
import com.example.falletterbackend.falletter.exception.notice.NoticeNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeDeleteService(
    private val noticeRepository: NoticeRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(id: Long) {
        val user = userFacade.getCurrentUser()

        if (user.role != Role.ADMIN) {
            throw AccessDeniedException
        }

        val notice = noticeRepository.findByIdOrNull(id)
            ?: throw NoticeNotFoundException

        noticeRepository.delete(notice)
    }
}
