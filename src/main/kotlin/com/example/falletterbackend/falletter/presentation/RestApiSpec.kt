package com.example.falletterbackend.falletter.presentation

object RestApiSpec {
    const val MAIL_VERIFY = "/email/verify"
    const val MAIL_MATCH = "/email/match"

    const val USER_SIGN_UP = "/signup"
    const val USER_SIGN_IN = "/signin"
    const val USER_LOG_OUT = "/logout"
    const val USER_USERS = "/users"

    const val COMMUNITY_CREATE_POST = "/posts"
    const val COMMUNITY_LIST_READ_POST = "/posts"
    const val COMMUNITY_READ_POST = "/posts/{post-id}"
    const val COMMUNITY_UPDATE_POST = "/posts/{post-id}"
    const val COMMUNITY_DELETE_POST = "/posts/{post-id}"
}