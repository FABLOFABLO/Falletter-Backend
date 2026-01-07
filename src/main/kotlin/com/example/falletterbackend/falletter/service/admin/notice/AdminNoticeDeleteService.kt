package com.example.falletterbackend.falletter.service.admin.notice

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminNoticeDeleteService(
    private val noticeFacade: NoticeFacade
) {
    @AdminOnly
    @CacheEvict(value = ["notices"], allEntries = true)
    @Transactional
    fun execute(id: Long) {
        val notice = noticeFacade.getNoticeById(id)

        noticeFacade.delete(notice)
    }
}
