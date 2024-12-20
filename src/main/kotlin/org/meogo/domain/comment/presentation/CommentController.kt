package org.meogo.domain.comment.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.meogo.domain.comment.presentation.dto.request.CommentRequest
import org.meogo.domain.comment.service.CreateCommentService
import org.meogo.domain.comment.service.DeleteCommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "Comment API")
@RestController
@RequestMapping("/comment")
class CommentController(
    private val createCommentService: CreateCommentService,
    private val deleteCommentService: DeleteCommentService
) {
    @Operation(summary = "댓글 작성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Valid @RequestBody
        request: CommentRequest
    ) = createCommentService.execute(request)

    @Operation(summary = "댓글 삭제")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestParam(name = "comment_id")id: Long) =
        deleteCommentService.execute(id)
}
