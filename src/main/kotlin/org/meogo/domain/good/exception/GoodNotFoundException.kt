package org.meogo.domain.good.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object GoodNotFoundException : MeogoException(
    ErrorCode.GOOD_NOT_FOUND
)
