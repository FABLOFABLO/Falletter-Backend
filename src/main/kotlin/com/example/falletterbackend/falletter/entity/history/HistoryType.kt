package com.example.falletterbackend.falletter.entity.history

enum class HistoryType (
    val state: String
){
    ATTENDANCE("출석 보상"),
    QUESTION_VOTE("질문 응답"),
    ETC("기타")
}
