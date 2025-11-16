package com.example.falletterbackend.falletter.presentation.history

import com.example.falletterbackend.falletter.dto.history.request.BrickSaveHistoryRequest
import com.example.falletterbackend.falletter.dto.history.response.BrickUsedHistoryResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.history.BrickSaveHistoryService
import com.example.falletterbackend.falletter.service.history.BrickUsedHistoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/history")
class HistoryController(
    private val brickUsedHistoryService: BrickUsedHistoryService,
    private val brickSaveHistoryService: BrickSaveHistoryService,
) {
    @PostMapping(RestApiSpec.HISTORY_SAVE)
    @ResponseStatus(HttpStatus.CREATED)
    fun saveHistory(@RequestBody @Valid request: BrickSaveHistoryRequest) {
        brickSaveHistoryService.execute(request)
    }

    @GetMapping(RestApiSpec.HISTORY_USED)
    @ResponseStatus(HttpStatus.OK)
    fun usedHistory(): List<BrickUsedHistoryResponse> {
        return brickUsedHistoryService.execute()
    }
}
