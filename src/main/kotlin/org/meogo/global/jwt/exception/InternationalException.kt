package org.meogo.global.jwt.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object InternationalException : MeogoException(
    ErrorCode.INTERNAL_SERVER_ERROR
)
