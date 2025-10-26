package com.example.falletterbackend.falletter.presentation

object RestApiSpec {
    const val MAIL_VERIFY = "/email/verify"
    const val MAIL_MATCH = "/email/match"

    const val USER_SIGN_UP = "/signup"
    const val USER_SIGN_IN = "/signin"
    const val USER_LOG_OUT = "/logout"
    const val USER_USERS = "/users"
    const val USER_ALL_STUDENT = "/student"

    const val COMMUNITY_CREATE_POST = "/posts"
    const val COMMUNITY_LIST_READ_POST = "/posts"
    const val COMMUNITY_READ_POST = "/posts/{post-id}"
    const val COMMUNITY_UPDATE_POST = "/posts/{post-id}"
    const val COMMUNITY_DELETE_POST = "/posts/{post-id}"

    const val COMMENT_CREATE = "/{post-id}"
    const val COMMENT_DELETE = "/{comment-id}"

    const val LETTER_BOX_SENT = "/sent"
    const val LETTER_BOX_SENT_DETAIL = "/sent/{letter-id}"
    const val LETTER_BOX_SENT_ALL = "/sent/all"
    const val LETTER_BOX_RECEIVED_DETAIL = "/received/{letter-id}"
    const val LETTER_BOX_RECEIVED_ALL = "/received/all"

    const val ITEM_LETTER_GET_COUNT = "/count"
    const val ITEM_LETTER_PATCH_COUNT = "/update"
    const val ITEM_BRICK_GET_COUNT = "/count"
    const val ITEM_BRICK_PATCH_COUNT = "/update"

    const val ANSWER_POST = "/choose"
    const val ANSWER_USED = "/used"
    const val ANSWER_PICK = "/chosen"

    const val QUESTION_ALL = "/all"
}