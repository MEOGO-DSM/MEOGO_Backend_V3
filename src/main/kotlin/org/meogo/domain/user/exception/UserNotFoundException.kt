package org.meogo.domain.user.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object UserNotFoundException : MeogoException(
    ErrorCode.USER_NOT_FOUND
)
