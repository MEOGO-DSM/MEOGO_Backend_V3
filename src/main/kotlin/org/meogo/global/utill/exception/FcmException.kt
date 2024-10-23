package org.meogo.global.utill.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object FcmException : MeogoException(
    ErrorCode.FCM_SERVER_ERROR
)
