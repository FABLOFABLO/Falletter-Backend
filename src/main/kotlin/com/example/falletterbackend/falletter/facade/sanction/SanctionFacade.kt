package com.example.falletterbackend.falletter.facade.sanction

import com.example.falletterbackend.falletter.entity.sanction.Sanction
import com.example.falletterbackend.falletter.entity.sanction.enums.SanctionType
import com.example.falletterbackend.falletter.entity.sanction.repository.SanctionRepository
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SanctionFacade(
    private val sanctionRepository: SanctionRepository
) {
    fun getSanctionsByUser(user: User): List<Sanction> {
        return sanctionRepository.findAllByUser(user)
    }

    fun getWarningCountByUser(user: User): Long {
        return sanctionRepository.countByUserAndType(user, SanctionType.WARNING)
    }

    fun getActiveSanction(user: User): Sanction? {
        return sanctionRepository.findByUserAndEndDateAfter(user, LocalDateTime.now())
    }

    fun createWarning(user: User): Sanction {
        val now = LocalDateTime.now()
        val warning = Sanction(
            user = user,
            type = SanctionType.WARNING,
            days = 0,
            blockReason = null,
            startDate = now,
            endDate = now
        )
        return sanctionRepository.save(warning)
    }

    fun createBlock(user: User, days: Int, reason: String): Sanction {
        val now = LocalDateTime.now()
        val sanction = Sanction(
            user = user,
            type = SanctionType.BLOCK,
            days = days,
            blockReason = reason,
            startDate = now,
            endDate = now.plusDays(days.toLong())
        )
        return sanctionRepository.save(sanction)
    }
}
