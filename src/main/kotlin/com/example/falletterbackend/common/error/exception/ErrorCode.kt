package com.example.falletterbackend.common.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    // auth
    TOKEN_INVALID(401, "Token Invalid"),
    TOKEN_EXPIRED(401, "Token Expired"),
    ACCESS_DENIED(403, "Access Denied"),
    UNMATCHED_VERIFY_CODE(403, "Unmatched Verify Code"),
    UNEXIST_VERIFY_CODE(403, "Unexist Verify Code"),

    // user
    UNMATCHED_PASSWORD(400, "Unmatched Password"),
    ALREADY_ACCOUNT_ID(409, "Already Exist Account Id"),
    ALREADY_EXIST_EMAIL(409, "Already Exist Email"),
    UNMISMATCH_USER(403, "UnMismatch User"),
    USER_NOT_FOUND(404, "User Not Found"),
    EMAIL_NOT_VERIFIED(403, "Email Not Verified"),


    // community
    COMMUNITY_NOT_FOUND(404, "Community Not Found"),
    COMMUNITY_ALREADY_DELETED(409, "Community Already Deleted"),

    // comment
    COMMENT_NOT_FOUND(404, "Comment Not Found"),
    COMMENT_ALREADY_DELETED(409, "Comment Already Deleted"),

    // item
    ITEM_NOT_FOUND(404, "Item Not Found"),
    BRICK_COUNT_INSUFFICIENT(409, "Brick Count Insufficient"),
    BRICK_HISTORY_NOT_FOUND(404, "Brick History Not Found"),

    // letter
    LETTER_NOT_FOUND(404, "Letter Not Found"),
    LETTER_ALREADY_SENT(409, "Letter Already Sent"),
    LETTER_COUNT_INSUFFICIENT(400, "Letter Count Insufficient"),
    LETTER_NO_ACCESS_PERMISSION(403, "Letter No Access Permission"),
    LETTER_NOT_RECEIVED(404, "Letter Not Received"),
    LETTER_NOT_SEND(404, "Letter Not Send"),

    // question
    QUESTION_NOT_FOUND(404, "Question Not Found"),

    // hint
    HINT_NOT_FOUND(404, "Hint Not Found"),

    // history
    HISTORY_NOT_FOUND(404, "History Not Found"),

    // answer
    ANSWER_NOT_FOUND(404, "Answer Not Found"),

    // notice
    NOTICE_NOT_FOUND(404, "Notice Not Found"),

    // internal
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}
