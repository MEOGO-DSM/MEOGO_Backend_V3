package org.meogo.domain.review.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object ReviewNotFoundException : MeogoException(
    ErrorCode.REVIEW_NOT_FOUND
)
