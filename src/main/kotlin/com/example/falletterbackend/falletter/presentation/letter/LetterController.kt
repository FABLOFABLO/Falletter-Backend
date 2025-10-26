package com.example.falletterbackend.falletter.presentation.letter

import com.example.falletterbackend.falletter.dto.item.request.LetterItemUpdateRequest
import com.example.falletterbackend.falletter.dto.item.response.LetterGetCountResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.letter.LetterGetCountService
import com.example.falletterbackend.falletter.service.letter.LetterItemUpdateService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/letter")
class LetterController(
    private val letterGetCountService: LetterGetCountService,
    private val letterItemUpdateService: LetterItemUpdateService
) {
    @GetMapping(RestApiSpec.LETTER_GET_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun getCountLetter(): LetterGetCountResponse {
        return letterGetCountService.execute()
    }

    @PatchMapping(RestApiSpec.LETTER_PATCH_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun letterItemUpdate(@RequestBody @Valid request: LetterItemUpdateRequest) {
        letterItemUpdateService.execute(request)
    }
}