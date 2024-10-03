package org.meogo.domain.good.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object AlreadyGoodException : MeogoException(
    ErrorCode.ALREADY_GOOD_EXCEPTION
)
