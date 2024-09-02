package org.meogo.global.jwt.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object InvalidJwtException : MeogoException(
    ErrorCode.INVALID_TOKEN
)
