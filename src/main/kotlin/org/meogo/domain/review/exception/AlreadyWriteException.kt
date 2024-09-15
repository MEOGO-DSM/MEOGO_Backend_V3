package org.meogo.domain.review.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object AlreadyWriteException : MeogoException(
    ErrorCode.ALREADY_WRITE_EXCEPTION
)
