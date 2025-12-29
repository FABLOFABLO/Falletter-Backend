package com.example.falletterbackend.falletter.dto.hint.request

data class HintUpdateRequest(
    val hintId : Long,
    val firstHint: String,
    val secondHint: String,
    val thirdHint: String
)