package org.meogo.domain.review.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object KeyWordNotFoundException : MeogoException(
    ErrorCode.KEYWORD_NOT_FOUND
)
