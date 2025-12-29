package com.example.falletterbackend.falletter.presentation.admin

import com.example.falletterbackend.falletter.dto.letter.response.AdminLetterUnpassedDetailsResponse
import com.example.falletterbackend.falletter.dto.letter.response.AdminLetterUnpassedListResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letter.AdminLetterUnpassedDetailsService
import com.example.falletterbackend.falletter.service.letter.AdminLetterUnpassedListService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminLetterUnpassedListService: AdminLetterUnpassedListService,
    private val adminLetterUnpassedDetailsService: AdminLetterUnpassedDetailsService
) {
    @GetMapping(RestApiSpec.LETTER_BOX_UNPASSED)
    @ResponseStatus(HttpStatus.OK)
    fun adminUnpassedAll(): List<AdminLetterUnpassedListResponse> {
        return adminLetterUnpassedListService.execute()
    }

    @GetMapping(RestApiSpec.LETTER_BOX_UNPASSED_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    fun adminUnpassedDetail(@PathVariable("letter-id") id: Long): AdminLetterUnpassedDetailsResponse {
        return adminLetterUnpassedDetailsService.execute(id)
    }
}