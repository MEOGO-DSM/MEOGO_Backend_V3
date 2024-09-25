package org.meogo.domain.question.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object QuestionNotFoundException : MeogoException(
    ErrorCode.QUESTION_NOT_FOUND
)
