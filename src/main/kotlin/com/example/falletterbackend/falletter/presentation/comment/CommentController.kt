package com.example.falletterbackend.falletter.presentation.comment

import com.example.falletterbackend.falletter.dto.comment.request.CommentRequest
import com.example.falletterbackend.falletter.presentation.RestApiSpec
import com.example.falletterbackend.falletter.service.comment.CommentDeleteService
import com.example.falletterbackend.falletter.service.comment.CommentWriterService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Comment", description = "댓글 API")
@RestController
@RequestMapping("/comment")
class CommentController(
    private val commentWriterService: CommentWriterService,
    private val commentDeleteService: CommentDeleteService
) {
    @Operation(summary = "댓글 작성", description = "게시글에 댓글을 작성합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "댓글 작성 성공"),
        ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    )
    @PostMapping(RestApiSpec.COMMENT_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun commentWrite(
        @Parameter(description = "게시글 ID", example = "1")
        @PathVariable("post-id") id: Long,
        @RequestBody @Valid request: CommentRequest
    ) {
        commentWriterService.execute(id, request)
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
        ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음")
    )
    @DeleteMapping(RestApiSpec.COMMENT_DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun commentDelete(
        @Parameter(description = "댓글 ID", example = "1")
        @PathVariable("comment-id") id: Long
    ) { commentDeleteService.execute(id) }
}
