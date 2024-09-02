package org.meogo.domain.user.presentation

import lombok.RequiredArgsConstructor
import org.meogo.domain.user.presentation.dto.request.UserCheckRequest
import org.meogo.domain.user.presentation.dto.request.UserSignInRequest
import org.meogo.domain.user.presentation.dto.request.UserSignUpRequest
import org.meogo.domain.user.service.CheckAccountIdService
import org.meogo.domain.user.service.UserSignInService
import org.meogo.domain.user.service.UserSignUpService
import org.meogo.global.jwt.dto.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
class UserController(
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
    private val userCheckAccountIdService: CheckAccountIdService
) {
    @PostMapping("/signup")
    fun signUp(
        @Valid @RequestBody
        request: UserSignUpRequest
    ): TokenResponse =
        userSignUpService.execute(request)

    @PostMapping("/signin")
    fun signIn(@RequestBody request: UserSignInRequest): TokenResponse =
        userSignInService.execute(request)

    @PostMapping("/check")
    fun checkAccountId(@RequestBody request: UserCheckRequest): Boolean =
        userCheckAccountIdService.execute(request)
}
