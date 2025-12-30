package com.example.falletterbackend.falletter.dto.comment.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "댓글 작성 요청")
data class CommentRequest(
    @Schema(description = "댓글 내용", example = "좋은 글이네요!")
    val comment: String
)
