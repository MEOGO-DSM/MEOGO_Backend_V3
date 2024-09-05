package org.meogo.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    PASSWORD_MISMATCH(401, "Password Mismatch"),
    USER_MISMATCH(401, "User Mismatch"),

    USER_NOT_FOUND(404, "User not found"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error")
}
