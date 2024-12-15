// package org.meogo.domain.question.service
//
// import org.junit.jupiter.api.BeforeEach
// import org.junit.jupiter.api.Test
// import org.junit.jupiter.api.extension.ExtendWith
// import org.meogo.domain.question.domain.QuestionRepository
// import org.meogo.domain.question.enum.QuestionType
// import org.meogo.domain.question.presentation.dto.request.QuestionRequest
// import org.meogo.domain.user.domain.User
// import org.meogo.domain.user.domain.UserRole
// import org.meogo.domain.user.facade.UserFacade
// import org.mockito.InjectMocks
// import org.mockito.Mock
// import org.mockito.kotlin.any
// import org.mockito.kotlin.verify
// import org.mockito.kotlin.whenever
// import org.springframework.boot.test.context.SpringBootTest
// import org.springframework.test.context.junit.jupiter.SpringExtension
//
// // @ActiveProfiles("test")
// @SpringBootTest
// @ExtendWith(SpringExtension::class)
// class CreateQuestionServiceTest {
//    @InjectMocks
//    private lateinit var createQuestionService: CreateQuestionService
//
//    @Mock
//    private lateinit var questionRepository: QuestionRepository
//
//    @Mock
//    private lateinit var userFacade: UserFacade
//    private lateinit var user: User
//
//    @BeforeEach
//    fun setUp() {
//        user = User(
//            name = "test",
//            accountId = "test",
//            password = "password",
//            role = UserRole.USER,
//            profile = "default"
//        )
//
//        whenever(userFacade.currentUser()).thenReturn(user)
//    }
//
//    @Test
//    fun 질문_생성() {
//        // given
//        val request = QuestionRequest(
//            content = "학교 재미있나요?",
//            schoolId = 1,
//            questionType = QuestionType.LIFE
//        )
//        // when
//        createQuestionService.execute(request)
//
//        // then
//        verify(questionRepository).save(any())
//    }
// }
