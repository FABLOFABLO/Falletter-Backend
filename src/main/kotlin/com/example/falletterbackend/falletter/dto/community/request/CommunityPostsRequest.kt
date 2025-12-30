package com.example.falletterbackend.falletter.dto.community.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "게시글 작성/수정 요청")
data class CommunityPostsRequest(
    @Schema(description = "게시글 제목", example = "오늘의 공지사항")
    val title: String,

    @Schema(description = "게시글 내용", example = "오늘 점심은 짜장면입니다.")
    val content: String
)
