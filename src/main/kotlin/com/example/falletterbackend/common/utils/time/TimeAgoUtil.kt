package com.example.falletterbackend.common.utils.time

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object TimeAgoUtil {

    fun getTimeAgo(dateTime: LocalDateTime): String {
        val now = LocalDateTime.now()

        val seconds = ChronoUnit.SECONDS.between(dateTime, now)
        val minutes = ChronoUnit.MINUTES.between(dateTime, now)
        val hours = ChronoUnit.HOURS.between(dateTime, now)
        val days = ChronoUnit.DAYS.between(dateTime, now)
        val weeks = days / 7
        val months = ChronoUnit.MONTHS.between(dateTime, now)

        return when {
            seconds < 60 -> "방금 전"
            minutes < 60 -> "${minutes}분 전"
            hours < 24 -> "${hours}시간 전"
            days < 7 -> "${days}일 전"
            weeks < 5 -> "${weeks}주 전"
            months < 12 -> "${months}개월 전"
            else -> {
                val year = dateTime.year
                val month = String.format("%02d", dateTime.monthValue)
                val day = String.format("%02d", dateTime.dayOfMonth)
                "${year}.${month}.${day}"
            }
        }
    }
}
