package org.meogo.domain.user.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.meogo.domain.user.presentation.dto.request.UserCheckRequest
import org.meogo.domain.user.presentation.dto.request.UserModifyRequest
import org.meogo.domain.user.presentation.dto.request.UserSignInRequest
import org.meogo.domain.user.presentation.dto.request.UserSignUpRequest
import org.meogo.domain.user.presentation.dto.response.MyPageResponse
import org.meogo.domain.user.service.CheckAccountIdService
import org.meogo.domain.user.service.ModifyUserInfoService
import org.meogo.domain.user.service.QueryMyPageService
import org.meogo.domain.user.service.UserSignInService
import org.meogo.domain.user.service.UserSignUpService
import org.meogo.global.jwt.dto.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@Tag(name = "User API")
@RestController
@RequestMapping("/user")
class UserController(
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
    private val userCheckAccountIdService: CheckAccountIdService,
    private val queryMyPageService: QueryMyPageService,
    private val modifyUserInfoService: ModifyUserInfoService
) {
    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(
        @Valid @RequestBody
        request: UserSignUpRequest
    ): TokenResponse =
        userSignUpService.execute(request)

    @Operation(summary = "로그인")
    @PostMapping("/signin")
    fun signIn(@RequestBody request: UserSignInRequest): TokenResponse =
        userSignInService.execute(request)

    @Operation(summary = "아이디 중복 여부 반환")
    @PostMapping("/check")
    fun checkAccountId(@RequestBody request: UserCheckRequest): Boolean =
        userCheckAccountIdService.execute(request)

    @Operation(summary = "마이페이지")
    @GetMapping("/my")
    fun myPage(): MyPageResponse =
        queryMyPageService.execute()

    @Operation(summary = "마이페이지 수정")
    @PatchMapping("/modify")
    fun updateProfile(
        @RequestPart(name = "image")
        file: MultipartFile?,
        @RequestPart(name = "request")
        request: UserModifyRequest
    ) = modifyUserInfoService.execute(request, file)
}
