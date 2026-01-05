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
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "item", description = "아이템 API")
@RestController
@RequestMapping("/item")
class ItemController(
    private val itemBrickGetCountService: ItemBrickGetCountService,
    private val itemBrickItemUpdateService: ItemBrickUpdateService,
    private val itemLetterGetCountService: ItemLetterGetCountService,
    private val itemLetterUpdateService: ItemLetterUpdateService
) {
    @Operation(summary = "벽돌 수량 조회", description = "현재 보유한 벽돌 수량을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.ITEM_BRICK_GET_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun getCountBrick(): ItemBrickGetCountResponse { return itemBrickGetCountService.execute() }

    @Operation(summary = "벽돌 수량 변경", description = "벽돌 수량을 변경합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "변경 성공")
    )
    @PatchMapping(RestApiSpec.ITEM_BRICK_PATCH_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun brickItemUpdate(@RequestBody @Valid request: ItemBrickItemUpdateRequest) { itemBrickItemUpdateService.execute(request) }

    @Operation(summary = "편지 수량 조회", description = "현재 보유한 편지 수량을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공")
    )
    @GetMapping(RestApiSpec.ITEM_LETTER_GET_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun getCountLetter(): ItemLetterGetCountResponse { return itemLetterGetCountService.execute() }

    @Operation(summary = "편지 수량 변경", description = "편지 수량을 변경합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "변경 성공")
    )
    @PatchMapping(RestApiSpec.ITEM_LETTER_PATCH_COUNT)
    @ResponseStatus(HttpStatus.OK)
    fun letterItemUpdate(@RequestBody @Valid request: ItemLetterItemUpdateRequest) { itemLetterUpdateService.execute(request) }
}
