package org.meogo.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    INVALID_STAR_RANGE(400, "Invalid start range"),

    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    PASSWORD_MISMATCH(401, "Password Mismatch"),

    USER_MISMATCH(403, "You are not owner"),

    USER_NOT_FOUND(404, "User not found"),
    REVIEW_NOT_FOUND(404, "Review not found"),
    POST_NOT_FOUND(404, "Post not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    KEYWORD_NOT_FOUND(404, "Keyword not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    BOOKMARK_NOT_FOUND(404, "Bookmark not found"),
    GOOD_NOT_FOUND(404, "Good Not found"),

    ALREADY_WRITE_EXCEPTION(409, "You have already submitted a review"),
    ALREADY_GOOD_EXCEPTION(409, "You have already booked"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    FCM_SERVER_ERROR(500, "FCM Server Error")
}
