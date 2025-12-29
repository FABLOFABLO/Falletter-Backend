package com.example.falletterbackend.falletter.presentation.hint

import com.example.falletterbackend.falletter.service.hint.HintGetService
import com.example.falletterbackend.falletter.service.hint.HintSaveService
import com.example.falletterbackend.falletter.service.hint.HintUpdateService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/hint")
class HintController(
    private val hintSaveService: HintSaveService,
    private val hintGetService: HintGetService,
    private val hintUpdateService: HintUpdateService
) {
}