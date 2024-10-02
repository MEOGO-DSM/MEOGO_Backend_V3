package org.meogo.domain.bookmark.exception

import org.meogo.global.error.exception.ErrorCode
import org.meogo.global.error.exception.MeogoException

object BookmarkNotFoundException : MeogoException(
    ErrorCode.BOOKMARK_NOT_FOUND
)
