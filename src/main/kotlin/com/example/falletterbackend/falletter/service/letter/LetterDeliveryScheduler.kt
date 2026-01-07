package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.facade.letter.LetterFacade
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LetterDeliveryScheduler(
    private val letterFacade: LetterFacade,
    @Value("\${falletter.scheduler.letter-delivery-threshold-hours}")
    private val deliveryThresholdHours: Long
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedRateString = "\${falletter.scheduler.letter-delivery-rate-ms}")
    @Transactional
    fun updateDeliveryStatus() {
        val threshold = LocalDateTime.now().minusHours(deliveryThresholdHours)
        val undeliveredLetters = letterFacade.getUndeliveredLettersBefore(threshold)

        if (undeliveredLetters.isNotEmpty()) {
            undeliveredLetters.forEach { letter ->
                letter.isDelivered = true
            }
            letterFacade.saveAllLetters(undeliveredLetters)
            log.info("${undeliveredLetters.size}개의 편지가 배달 완료 처리되었습니다.")
        }
    }
}
