package org.meogo.domain.comment.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object CommentNotFoundException : MeogoException(
    ErrorCode.COMMENT_NOT_FOUND
)
