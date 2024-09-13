package org.meogo.domain.post.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object PostNotFoundException : MeogoException(
    ErrorCode.POST_NOT_FOUND
)
