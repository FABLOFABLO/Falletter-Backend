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
    const val LETTER_BOX_UNPASSED = "/letter/unpassed"
    const val LETTER_BOX_UNPASSED_DETAIL = "/letter/unpassed/{letter-id}"

    const val ITEM_LETTER_GET_COUNT = "/letter/count"
    const val ITEM_LETTER_PATCH_COUNT = "/letter/update"
    const val ITEM_BRICK_GET_COUNT = "/brick/count"
    const val ITEM_BRICK_PATCH_COUNT = "/brick/update"

    const val HISTORY_SAVE = "/brick/save"
    const val HISTORY_USED = "/brick/used"

    const val ANSWER_POST = "/choose"
    const val ANSWER_PICK = "/chosen"

    const val QUESTION_ALL = "/all"

    const val HINT_SAVE = "/save"
    const val HINT_UPDATE = "/update"
    const val HINT_GET_ALL = "/{answer-id}"

    const val NOTICE_CREATE = "/notice"
    const val NOTICE_LIST = "/notice"
    const val NOTICE_DETAIL = "/notice/{notice-id}"
    const val NOTICE_DELETE = "/notice/{notice-id}"

    const val ADMIN_COMMUNITY_STATUS = "/community/{community-id}"

    const val ADMIN_USER_LIST = "/user/all"
    const val ADMIN_USER_PROFILE = "/user/{user-id}"
    const val ADMIN_USER_WARNING = "/user/{user-id}/warning"
    const val ADMIN_USER_SANCTION = "/user/{user-id}/sanction"

    const val TIMER_BRICK_POST = "/brick"
    const val TIMER_BRICK_GET = "/brick"

    const val TIMER_ROULETTE_POST = "/roulette"
    const val TIMER_ROULETTE_GET = "/roulette"

    const val SUSPEND_ALL = "/all"
    const val SUSPEND_DETAIL = "/{suspend-id}"

    const val NOTIFICATION_GET = "/setting"
    const val NOTIFICATION_UPDATE = "/setting"

    const val DEVICE_TOKEN_REGISTER = "/token"
    const val DEVICE_TOKEN_DELETE = "/token/{device-id}"
}
