package com.example.falletterbackend.falletter.service.letter

import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LetterDeliveryScheduler(
    private val letterRepository: LetterRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedRate = 180000) // 3분마다 실행
    @Transactional
    fun updateDeliveryStatus() {
        val threshold = LocalDateTime.now().minusHours(12)
        val undeliveredLetters = letterRepository.findAllUndeliveredBefore(threshold)

        if (undeliveredLetters.isNotEmpty()) {
            undeliveredLetters.forEach { letter ->
                letter.isDelivered = true
            }
            letterRepository.saveAll(undeliveredLetters)
            log.info("${undeliveredLetters.size}개의 편지가 배달 완료 처리되었습니다.")
        }
    }
}
