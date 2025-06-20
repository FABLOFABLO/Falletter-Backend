package com.example.falletterbackend.falletter.presentation.brick

import com.example.falletterbackend.falletter.dto.brick.response.BrickGetCountResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.brick.BrickGetCountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/brick")
class BrickController(
    private val brickGetCountService: BrickGetCountService
) {
    @GetMapping(RestApiSpec.BRICK_GET_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun getCountBrick(): BrickGetCountResponse {
        return brickGetCountService.execute()
    }
}