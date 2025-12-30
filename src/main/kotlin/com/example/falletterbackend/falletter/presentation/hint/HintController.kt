package com.example.falletterbackend.falletter.presentation.hint

import com.example.falletterbackend.falletter.dto.hint.request.HintSaveRequest
import com.example.falletterbackend.falletter.dto.hint.request.HintUpdateRequest
import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.hint.HintGetAllService
import com.example.falletterbackend.falletter.service.hint.HintSaveService
import com.example.falletterbackend.falletter.service.hint.HintUpdateService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/hint")
class HintController(
    private val hintSaveService: HintSaveService,
    private val hintGetAllService: HintGetAllService,
    private val hintUpdateService: HintUpdateService
) {
    @PostMapping(RestApiSpec.HINT_SAVE)
    @ResponseStatus(HttpStatus.CREATED)
    fun saveHint(@RequestBody request: HintSaveRequest) {
        hintSaveService.execute(request)
    }

    @GetMapping(RestApiSpec.HINT_GET_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun getAllHint(@PathVariable("answer-id") id: Long): HintResponse {
        return hintGetAllService.execute(id)
    }

    @PatchMapping(RestApiSpec.HINT_UPDATE)
    @ResponseStatus(HttpStatus.OK)
    fun updateHint(@RequestBody request: HintUpdateRequest) {
        hintUpdateService.execute(request)
    }
}