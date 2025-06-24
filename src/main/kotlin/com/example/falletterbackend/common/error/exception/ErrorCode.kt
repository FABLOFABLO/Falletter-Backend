package com.example.falletterbackend.common.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    INCORRECT_PASSWORD(400, "Incorrect Password"),

    TOKEN_INVALID(401, "Token Invalid"),
    TOKEN_EXPIRED(401, "Token Expired"),

    UNMATCHED_VERIFY_CODE(403, "Unmatched Verify Code"),
    UNEXIST_VERIFY_CODE(403, "Unexist Verify Code"),
    UNMISMATCH_USER(403, "UnMismatch User"),

    USER_NOT_FOUND(404, "User Not Found"),
    POST_NOT_FOUND(404, "Post Not Found"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}