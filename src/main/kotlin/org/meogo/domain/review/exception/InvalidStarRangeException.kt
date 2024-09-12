package org.meogo.domain.review.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object InvalidStarRangeException : MeogoException(
    ErrorCode.INVALID_STAR_RANGE
)
