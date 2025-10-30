package com.example.falletterbackend.falletter.presentation.item

import com.example.falletterbackend.falletter.dto.item.request.ItemBrickItemUpdateRequest
import com.example.falletterbackend.falletter.dto.item.request.ItemLetterItemUpdateRequest
import com.example.falletterbackend.falletter.dto.item.response.ItemBrickGetCountResponse
import com.example.falletterbackend.falletter.dto.item.response.ItemLetterGetCountResponse
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.item.ItemBrickGetCountService
import com.example.falletterbackend.falletter.service.item.ItemBrickUpdateService
import com.example.falletterbackend.falletter.service.item.ItemLetterGetCountService
import com.example.falletterbackend.falletter.service.item.ItemLetterUpdateService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item")
class ItemController(
    private val itemBrickGetCountService: ItemBrickGetCountService,
    private val itemBrickItemUpdateService: ItemBrickUpdateService,
    private val itemLetterGetCountService: ItemLetterGetCountService,
    private val itemLetterUpdateService: ItemLetterUpdateService
) {
    @GetMapping(RestApiSpec.ITEM_BRICK_GET_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun getCountBrick(): ItemBrickGetCountResponse {
        return itemBrickGetCountService.execute()
    }

    @PatchMapping(RestApiSpec.ITEM_BRICK_PATCH_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun brickItemUpdate(@RequestBody @Valid request: ItemBrickItemUpdateRequest) {
        itemBrickItemUpdateService.execute(request)
    }

    @GetMapping(RestApiSpec.ITEM_LETTER_GET_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun getCountLetter(): ItemLetterGetCountResponse {
        return itemLetterGetCountService.execute()
    }

    @PatchMapping(RestApiSpec.ITEM_LETTER_PATCH_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun letterItemUpdate(@RequestBody @Valid request: ItemLetterItemUpdateRequest) {
        itemLetterUpdateService.execute(request)
    }
}
