package com.example.falletterbackend.falletter.facade.suspend

import com.example.falletterbackend.falletter.entity.suspend.Suspend
import com.example.falletterbackend.falletter.entity.suspend.enums.SuspendType
import com.example.falletterbackend.falletter.entity.suspend.repository.SuspendRepository
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SuspendFacade(
    private val suspendRepository: SuspendRepository
) {
    fun getSuspendsByUser(user: User): List<Suspend> {
        return suspendRepository.findAllByUser(user)
    }

    fun getWarningCountByUser(user: User): Long {
        return suspendRepository.countByUserAndType(user, SuspendType.WARNING)
    }

    fun getWarningCountsByUserIds(userIds: List<Long>): Map<Long, Long> {
        return suspendRepository.countWarningsByUserIds(userIds, SuspendType.WARNING)
    }

    fun getActiveSuspend(user: User): Suspend? {
        return suspendRepository.findByUserAndEndDateAfter(user, LocalDateTime.now())
    }

    fun createWarning(user: User): Suspend {
        val now = LocalDateTime.now()
        val warning = Suspend(
            user = user,
            type = SuspendType.WARNING,
            days = 0,
            blockReason = null,
            startDate = now,
            endDate = now
        )
        return suspendRepository.save(warning)
    }

    fun createBlock(user: User, days: Int, reason: String): Suspend {
        val now = LocalDateTime.now()
        val suspend = Suspend(
            user = user,
            type = SuspendType.BLOCK,
            days = days,
            blockReason = reason,
            startDate = now,
            endDate = now.plusDays(days.toLong())
        )
        return suspendRepository.save(suspend)
    }

}
