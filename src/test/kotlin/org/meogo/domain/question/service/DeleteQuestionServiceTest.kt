// package org.meogo.domain.question.service
//
// import org.junit.jupiter.api.BeforeEach
// import org.junit.jupiter.api.Test
// import org.junit.jupiter.api.extension.ExtendWith
// import org.meogo.domain.question.domain.QuestionRepository
// import org.meogo.domain.user.domain.User
// import org.meogo.domain.user.domain.UserRole
// import org.meogo.domain.user.facade.UserFacade
// import org.mockito.InjectMocks
// import org.mockito.Mock
// import org.mockito.kotlin.whenever
// import org.springframework.boot.test.context.SpringBootTest
// import org.springframework.test.context.junit.jupiter.SpringExtension
//
// // @ActiveProfiles("test")
// @SpringBootTest
// @ExtendWith(SpringExtension::class)
// class DeleteQuestionServiceTest {
//
//    @InjectMocks
//    private lateinit var deleteQuestionService: DeleteQuestionService
//
//    @Mock
//    private lateinit var questionRepository: QuestionRepository
//
//    @Mock
//    private lateinit var userFacade: UserFacade
//
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
//    fun execute() {
//        // given
// //        val
//    }
// }
