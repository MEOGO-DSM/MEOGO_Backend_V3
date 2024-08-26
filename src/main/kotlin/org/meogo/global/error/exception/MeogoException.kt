package org.meogo.global.error.exception

abstract class MeogoException(
    val errorCode: ErrorCode
) : RuntimeException()
