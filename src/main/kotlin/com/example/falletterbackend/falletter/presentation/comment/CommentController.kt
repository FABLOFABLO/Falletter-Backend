package com.example.falletterbackend.falletter.presentation.comment

import com.example.falletterbackend.falletter.dto.comment.request.CommentRequest
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.comment.CommentDeleteService
import com.example.falletterbackend.falletter.service.comment.CommentWriterService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comment")
class CommentController(
    private val commentWriterService: CommentWriterService,
    private val commentDeleteService: CommentDeleteService
) {
    @PostMapping(RestApiSpec.COMMENT_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun commentWrite(
        @PathVariable("post-id") id: Long,
        @RequestBody @Valid request: CommentRequest
    ) {
        commentWriterService.execute(id, request)
    }

    @DeleteMapping(RestApiSpec.COMMENT_DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun commentDelete(@PathVariable("comment-id") id: Long) { commentDeleteService.execute(id) }
}
