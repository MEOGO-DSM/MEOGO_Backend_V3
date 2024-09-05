package org.meogo.domain.user.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object UserMisMatchException : MeogoException(
    ErrorCode.USER_MISMATCH
)
