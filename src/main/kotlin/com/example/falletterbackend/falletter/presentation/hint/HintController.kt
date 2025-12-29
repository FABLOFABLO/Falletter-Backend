package com.example.falletterbackend.falletter.presentation.hint

import com.example.falletterbackend.falletter.dto.hint.request.HintRequest
import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.entity.hint.Hint
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.hint.HintGetService
import com.example.falletterbackend.falletter.service.hint.HintSaveService
import com.example.falletterbackend.falletter.service.hint.HintUpdateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/hint")
class HintController(
    private val hintSaveService: HintSaveService,
    private val hintGetService: HintGetService,
    private val hintUpdateService: HintUpdateService
) {
    @PostMapping(RestApiSpec.HINT_SAVE)
    fun saveHint(@RequestBody request: HintRequest) {
        hintSaveService.execute(request)
    }

    @GetMapping(RestApiSpec.HINT_GET_ALL)
    fun getAllHint(@PathVariable("answer-id") id: Long): HintResponse {
        return hintGetService.execute(id)
    }

    @PatchMapping(RestApiSpec.HINT_UPDATE)
    fun updateHint(@RequestBody request: HintRequest) {
    }
}