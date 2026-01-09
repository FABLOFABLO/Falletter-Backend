package com.example.falletterbackend.falletter.entity.suspend.repository

import com.example.falletterbackend.falletter.entity.suspend.enums.SuspendType

interface SuspendRepositoryCustom {
    fun countWarningsByUserIds(userIds: List<Long>, type: SuspendType): Map<Long, Long>
}
