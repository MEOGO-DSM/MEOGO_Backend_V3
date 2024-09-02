package org.meogo.domain.user.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object PasswordMismatchException : MeogoException(
    ErrorCode.PASSWORD_MISMATCH
)
